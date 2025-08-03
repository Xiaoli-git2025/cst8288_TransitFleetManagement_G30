package model;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Student ID: 040469755
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
/**
 * RouteDTO represents a data transfer object for the 'Route' table.
 * It is used to transfer route data between application layers (e.g., controller, service, DAO).
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
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

     /**
     * Constructs a RouteDTO with specified values.
     *
     * @param routeId      the unique identifier of the route
     * @param routeNumber  the display number of the route
     * @param description  the textual description of the route
     */
    public RouteDTO(int routeId, int routeNumber, String description) {
        this.routeId = routeId;
        this.routeNumber = routeNumber;
        this.description = description;
    }

    /**
     * Gets the route ID.
     *
     * @return the route ID
     */
    public int getRouteId() {
        return routeId;
    }

    /**
     * Sets the route ID.
     *
     * @param routeId the route ID to set
     */
    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    /**
     * Gets the route number.
     *
     * @return the route number
     */
    public int getRouteNumber() {
        return routeNumber;
    }

    /**
     * Sets the route number.
     *
     * @param routeNumber the route number to set
     */
    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    /**
     * Gets the route description.
     *
     * @return the description of the route
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the route description.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}