package test;

import business.AlertTypeBusinessLogic;
import model.AlertDTO;
import org.junit.*;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class AlertTypeBusinessLogicTest {

    private static AlertTypeBusinessLogic alertLogic;
    private static int testAlertId;

    @BeforeClass
    public static void setUpClass() {
        alertLogic = new AlertTypeBusinessLogic();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        if (testAlertId > 0) {
            alertLogic.deleteAlert(testAlertId); // Clean up test data
        }
    }

    @Test
    public void testAddAlert() throws SQLException {
        AlertDTO alert = new AlertDTO(0, "TestType", "TestDescription");
        boolean result = alertLogic.addAlert(alert);
        assertTrue("Alert should be added successfully", result);

        // Retrieve the newly added alert by checking all alerts and saving its ID
        List<AlertDTO> allAlerts = alertLogic.getAllAlert();
        boolean found = false;
        for (AlertDTO a : allAlerts) {
            if (a.getAlertType().equals("TestType") && a.getAlertDescription().equals("TestDescription")) {
                testAlertId = a.getAlertId(); // Save for cleanup
                found = true;
                break;
            }
        }
        assertTrue("Newly added alert should exist in list", found);
    }

    @Test
    public void testGetAllAlert() throws SQLException {
        List<AlertDTO> alerts = alertLogic.getAllAlert();
        assertNotNull("Alert list should not be null", alerts);
    }

    @Test
    public void testUpdateAlert() throws SQLException {
        if (testAlertId == 0) {
            testAddAlert(); // Ensure we have an alert to update
        }

        AlertDTO alert = alertLogic.getAlertById(testAlertId);
        assertNotNull("Fetched alert should not be null", alert);

        alert.setAlertType("UpdatedType");
        alert.setAlertDescription("Updated description");
        boolean updated = alertLogic.updateAlert(alert);
        assertTrue("Alert should be updated successfully", updated);

        AlertDTO updatedAlert = alertLogic.getAlertById(testAlertId);
        assertEquals("UpdatedType", updatedAlert.getAlertType());
        assertEquals("Updated description", updatedAlert.getAlertDescription());
    }

    @Test
    public void testDeleteAlert() throws SQLException {
        AlertDTO alert = new AlertDTO(0, "DeleteTestType", "To be deleted");
        alertLogic.addAlert(alert);

        // Fetch the alert we just added
        int deleteId = -1;
        for (AlertDTO a : alertLogic.getAllAlert()) {
            if (a.getAlertType().equals("DeleteTestType")) {
                deleteId = a.getAlertId();
                break;
            }
        }

        assertTrue("Inserted alert for deletion should be found", deleteId > 0);
        boolean deleted = alertLogic.deleteAlert(deleteId);
        assertTrue("Alert should be deleted successfully", deleted);
    }
}
