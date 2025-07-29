package test;

import java.math.BigDecimal;
import java.sql.Date;
import model.MaintenanceScheduleDTO;

public class MaintenanceScheduleTest {

    public static void main(String[] args) {
        // Create a MaintenanceScheduleDTO object using constructor
        MaintenanceScheduleDTO schedule = new MaintenanceScheduleDTO(
                1,                         // scheduleId
                101,                       // maintenanceId
                Date.valueOf("2025-08-15"),// scheduleDate
                "Oil change scheduled",    // note
                new BigDecimal("99.99")    // maintenanceCost
        );

        // Set completed status
        schedule.setCompleted(true);

        // Print the values using getters
        System.out.println("Schedule ID: " + schedule.getScheduleId());
        System.out.println("Maintenance ID: " + schedule.getMaintenanceId());
        System.out.println("Schedule Date: " + schedule.getScheduleDate());
        System.out.println("Note: " + schedule.getNote());
        System.out.println("Maintenance Cost: $" + schedule.getMaintenanceCost());
        System.out.println("Completed: " + schedule.isCompleted());
    }
}
