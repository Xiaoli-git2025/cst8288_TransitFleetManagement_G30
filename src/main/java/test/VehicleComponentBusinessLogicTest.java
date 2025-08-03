package test;

import business.VehicleComponentBusinessLogic;
import model.VehicleComponentDTO;

public class VehicleComponentBusinessLogicTest {
    public static void main(String[] args) {
        VehicleComponentBusinessLogic logic = new VehicleComponentBusinessLogic();
        
        // Test CREATE operation
        System.out.println("\n=== Testing CREATE Operation ===");
        VehicleComponentDTO newComponent = new VehicleComponentDTO();
        newComponent.setComponentName("Test Engine");
        newComponent.setVehicleId(1);  // Ensure this exists in DB
        newComponent.setUsedHour(0);
        newComponent.setThresholdHour(1000);
        
        boolean createSuccess = logic.addObject(newComponent);
        System.out.println("CREATE result: " + (createSuccess ? "SUCCESS" : "FAILED"));
        
        if (!createSuccess) {
            System.out.println("Stopping tests - create failed");
            return;
        }
        
        // Get the created component ID (simplified approach)
        int testComponentId = findTestComponentId(logic, "Test Engine");
        System.out.println("Created component ID: " + testComponentId);
        
        // Test UPDATE operation
        System.out.println("\n=== Testing UPDATE Operation ===");
        VehicleComponentDTO updatedComponent = new VehicleComponentDTO();
        updatedComponent.setComponentId(testComponentId);
        updatedComponent.setComponentName("Updated Engine");
        updatedComponent.setVehicleId(1);
        updatedComponent.setUsedHour(100);
        updatedComponent.setThresholdHour(1500);
        
        boolean updateSuccess = logic.updateObject(updatedComponent);
        System.out.println("UPDATE result: " + (updateSuccess ? "SUCCESS" : "FAILED"));
        
        // Test DELETE operation
        System.out.println("\n=== Testing DELETE Operation ===");
        boolean deleteSuccess = logic.deleteObject(testComponentId);
        System.out.println("DELETE result: " + (deleteSuccess ? "SUCCESS" : "FAILED"));
        
        // Verify deletion
        System.out.println("\n=== Verifying DELETE ===");
        VehicleComponentDTO deletedComponent = logic.getObjById(testComponentId);
        System.out.println("Verification: " + 
            (deletedComponent == null ? "PASSED (component not found)" : "FAILED (component still exists)"));
    }
    
    // Helper method to find our test component
    private static int findTestComponentId(VehicleComponentBusinessLogic logic, String componentName) {
        try {
            return logic.getAllObjects()
                      .stream()
                      .filter(c -> c.getComponentName().equals(componentName))
                      .findFirst()
                      .get()
                      .getComponentId();
        } catch (Exception e) {
            System.err.println("Error finding test component: " + e.getMessage());
            return -1;
        }
    }
}