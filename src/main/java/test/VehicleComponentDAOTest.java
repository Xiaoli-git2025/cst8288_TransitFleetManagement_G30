package test;

import dao.VehicleComponentDAO;
import model.VehicleComponentDTO;
import java.util.List;

public class VehicleComponentDAOTest {
    public static void main(String[] args) {
        VehicleComponentDAO dao = new VehicleComponentDAO();
        
        // Test CREATE operation
        System.out.println("\n=== Testing CREATE operation ===");
        VehicleComponentDTO newComponent = new VehicleComponentDTO();
        newComponent.setComponentName("Test Brakes");
        newComponent.setVehicleId(1);  // Ensure this vehicle exists in DB
        newComponent.setUsedHour(50);
        newComponent.setThresholdHour(500);
        
        boolean isCreated = dao.add(newComponent);
        System.out.println("Create operation: " + (isCreated ? "SUCCESS" : "FAILED"));
        
        if (!isCreated) {
            System.out.println("Stopping tests - create failed");
            return;
        }
        
        // Get the ID of the newly created component
        int newComponentId = getLastInsertedId(dao);
        System.out.println("Created component ID: " + newComponentId);
        
        // Test UPDATE operation
        System.out.println("\n=== Testing UPDATE operation ===");
        VehicleComponentDTO updatedComponent = new VehicleComponentDTO();
        updatedComponent.setComponentId(newComponentId);
        updatedComponent.setComponentName("Updated Brakes");
        updatedComponent.setVehicleId(1);
        updatedComponent.setUsedHour(75);
        updatedComponent.setThresholdHour(600);
        
        boolean isUpdated = dao.update(updatedComponent);
        System.out.println("Update operation: " + (isUpdated ? "SUCCESS" : "FAILED"));}
        /*
        // Test DELETE operation
        System.out.println("\n=== Testing DELETE operation ===");
        boolean isDeleted = dao.delete(newComponentId);
        System.out.println("Delete operation: " + (isDeleted ? "SUCCESS" : "FAILED"));
        
        // Verify deletion
        System.out.println("\n=== Verifying DELETE operation ===");
        VehicleComponentDTO deletedComponent = dao.getById(newComponentId);
        System.out.println("Verification: " + (deletedComponent == null ? "Component successfully deleted" : "Component still exists!"));
    }
    */
    private static int getLastInsertedId(VehicleComponentDAO dao) {
        try {
            List<VehicleComponentDTO> components = dao.getAll();
            if (!components.isEmpty()) {
                return components.get(components.size() - 1).getComponentId();
            }
        } catch (Exception e) {
            System.err.println("Error getting last inserted ID: " + e.getMessage());
        }
        return -1;
    }
}