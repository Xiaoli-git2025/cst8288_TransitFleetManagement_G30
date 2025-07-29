package test;

import model.RouteDTO;

public class RouteTest {

    public static void main(String[] args) {
        // Create a RouteDTO object using constructor
        RouteDTO route = new RouteDTO(1, 101, "Downtown to Airport Express");

        // Print values using getters
        System.out.println("Route ID: " + route.getRouteId());
        System.out.println("Route Number: " + route.getRouteNumber());
        System.out.println("Description: " + route.getDescription());

        // Update some values using setters
        route.setRouteNumber(202);
        route.setDescription("Uptown Circular Route");

        // Print updated values
        System.out.println("\nUpdated Route Number: " + route.getRouteNumber());
        System.out.println("Updated Description: " + route.getDescription());
    }
}
