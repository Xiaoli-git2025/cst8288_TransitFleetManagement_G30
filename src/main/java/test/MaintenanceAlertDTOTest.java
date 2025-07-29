package test;

import java.sql.Date;
import model.MaintenanceAlertDTO;

public class MaintenanceAlertDTOTest {

    public static void main(String[] args) {
        // Create an instance using the full constructor
        MaintenanceAlertDTO alert = new MaintenanceAlertDTO(
            1,               // maintenanceId
            101,             // alertId
            202,             // componentId
            Date.valueOf("2025-07-28"), // alertDate
            303,             // reporterId
            false            // resolved
        );

        // Print object state
        System.out.println("Maintenance ID: " + alert.getMaintenanceId());
        System.out.println("Alert ID: " + alert.getAlertId());
        System.out.println("Component ID: " + alert.getComponentId());
        System.out.println("Alert Date: " + alert.getAlertDate());
        System.out.println("Reporter ID: " + alert.getReporterId());
        System.out.println("Resolved: " + alert.isResolved());

        // Modify a field
        alert.setResolved(true);
        System.out.println("Updated Resolved: " + alert.isResolved());
    }
}
