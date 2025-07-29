package test;

import dao.AlertDAO;
import model.AlertDTO;

import java.util.List;

public class TestAlertDTO {
    public static void main(String[] args) {
        AlertDAO dao = new AlertDAO();

        // 1. Create a new AlertDTO and add it
        AlertDTO newAlert = new AlertDTO();
        newAlert.setAlertType("Warning");
        newAlert.setAlertDescription("Low disk space");

        boolean added = dao.add(newAlert);
        System.out.println("Added new alert: " + added);

        // 2. Get all alerts
        try {
            List<AlertDTO> alerts = dao.getAll();
            System.out.println("All alerts:");
            for (AlertDTO alert : alerts) {
                System.out.println(alert.getAlertId() + ": " + alert.getAlertType() + " - " + alert.getAlertDescription());
            }

            // 3. Get alert by ID (if list not empty)
            if (!alerts.isEmpty()) {
                int testId = alerts.get(0).getAlertId();
                AlertDTO foundAlert = dao.getById(testId);
                System.out.println("Found alert with ID " + testId + ": " + foundAlert.getAlertType() + " - " + foundAlert.getAlertDescription());

                // 4. Update the found alert
                foundAlert.setAlertDescription("Low disk space - urgent");
                boolean updated = dao.update(foundAlert);
                System.out.println("Updated alert: " + updated);

                // 5. Delete the alert
                boolean deleted = dao.delete(foundAlert.getAlertId());
                System.out.println("Deleted alert: " + deleted);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
