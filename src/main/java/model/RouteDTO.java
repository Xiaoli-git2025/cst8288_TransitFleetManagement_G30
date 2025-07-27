package model;

/**
 * RouteDTO represents a data transfer object for the 'Route' table.
 * It is used to transfer route data between application layers (e.g., controller, service, DAO).
 */
public class RouteDTO {

    // Unique identifier for the route (Primary Key, auto-incremented)
    private int routeId;

    // Unique route number assigned to the route (e.g., 101, 202)
    private int routeNumber;

    // Description of the route (can include details like destination, stops, etc.)
    private String description;

    // Constructors
    public RouteDTO() {
    }

    public RouteDTO(int routeId, int routeNumber, String description) {
        this.routeId = routeId;
        this.routeNumber = routeNumber;
        this.description = description;
    }

    // Getters and Setters
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
