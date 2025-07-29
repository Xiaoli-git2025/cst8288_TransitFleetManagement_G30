package test;

import java.sql.Time;
import model.RouteScheduleDTO;

public class RouteScheduleTest {

    public static void main(String[] args) {
        // Create sample arrival and departure times
        Time arrivalTime = Time.valueOf("08:30:00");
        Time departureTime = Time.valueOf("08:45:00");

        // Create a RouteScheduleDTO instance
        RouteScheduleDTO schedule = new RouteScheduleDTO(
            1,         // scheduleId
            101,       // routeId
            5,         // stationId
            1,         // scheduleNumber
            arrivalTime,
            departureTime
        );

        // Display original values
        System.out.println("Schedule ID: " + schedule.getScheduleId());
        System.out.println("Route ID: " + schedule.getRouteId());
        System.out.println("Station ID: " + schedule.getStationId());
        System.out.println("Schedule Number: " + schedule.getScheduleNumber());
        System.out.println("Arrival Time: " + schedule.getScheduleArriveTime());
        System.out.println("Departure Time: " + schedule.getScheduleDepartTime());

        // Modify values using setters
        schedule.setScheduleNumber(2);
        schedule.setScheduleArriveTime(Time.valueOf("09:00:00"));
        schedule.setScheduleDepartTime(Time.valueOf("09:15:00"));

        // Display updated values
        System.out.println("\nUpdated Schedule Number: " + schedule.getScheduleNumber());
        System.out.println("Updated Arrival Time: " + schedule.getScheduleArriveTime());
        System.out.println("Updated Departure Time: " + schedule.getScheduleDepartTime());
    }
}
