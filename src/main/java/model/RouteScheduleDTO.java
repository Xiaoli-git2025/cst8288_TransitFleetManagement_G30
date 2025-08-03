package model;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
/**
 * RouteScheduleDTO is a Data Transfer Object for the 'RouteSchedule' table.
 * It represents a scheduled stop for a specific route at a particular station,
 * including arrival and departure times.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class RouteScheduleDTO {

    // Unique identifier for the schedule entry (Primary Key, auto-incremented)
    private int scheduleId;

    // Foreign key referring to the associated route
    private int routeId;

    // Foreign key referring to the associated station
    private int stationId;

    // Number used to define the order of stops within the route schedule
    private int scheduleNumber;

    // Scheduled arrival time at the station
    private java.sql.Time scheduleArriveTime;

    // Scheduled departure time from the station
    private java.sql.Time scheduleDepartTime;

    // Constructors
    public RouteScheduleDTO() {}

    /**
     * Constructs a RouteScheduleDTO with all fields.
     *
     * @param scheduleId         the unique ID of the schedule
     * @param routeId            the ID of the route
     * @param stationId          the ID of the station
     * @param scheduleNumber     the sequence number of the schedule
     * @param scheduleArriveTime the scheduled arrival time at the station
     * @param scheduleDepartTime the scheduled departure time from the station
     */
    public RouteScheduleDTO(int scheduleId, int routeId, int stationId,
                            int scheduleNumber, java.sql.Time scheduleArriveTime, java.sql.Time scheduleDepartTime) {
        this.scheduleId = scheduleId;
        this.routeId = routeId;
        this.stationId = stationId;
        this.scheduleNumber = scheduleNumber;
        this.scheduleArriveTime = scheduleArriveTime;
        this.scheduleDepartTime = scheduleDepartTime;
    }

    /**
     * Gets the schedule ID.
     *
     * @return the schedule ID
     */
    public int getScheduleId() {
        return scheduleId;
    }

    /**
     * Sets the schedule ID.
     *
     * @param scheduleId the schedule ID to set
     */
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
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
     * Gets the station ID.
     *
     * @return the station ID
     */
    public int getStationId() {
        return stationId;
    }

    /**
     * Sets the station ID.
     *
     * @param stationId the station ID to set
     */
    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    /**
     * Gets the schedule number (order in the route).
     *
     * @return the schedule number
     */
    public int getScheduleNumber() {
        return scheduleNumber;
    }

    /**
     * Sets the schedule number (order in the route).
     *
     * @param scheduleNumber the schedule number to set
     */
    public void setScheduleNumber(int scheduleNumber) {
        this.scheduleNumber = scheduleNumber;
    }

    /**
     * Gets the scheduled arrival time.
     *
     * @return the scheduled arrival time
     */
    public java.sql.Time getScheduleArriveTime() {
        return scheduleArriveTime;
    }

    /**
     * Sets the scheduled arrival time.
     *
     * @param scheduleArriveTime the arrival time to set
     */
    public void setScheduleArriveTime(java.sql.Time scheduleArriveTime) {
        this.scheduleArriveTime = scheduleArriveTime;
    }

    /**
     * Gets the scheduled departure time.
     *
     * @return the scheduled departure time
     */
    public java.sql.Time getScheduleDepartTime() {
        return scheduleDepartTime;
    }

    /**
     * Sets the scheduled departure time.
     *
     * @param scheduleDepartTime the departure time to set
     */
    public void setScheduleDepartTime(java.sql.Time scheduleDepartTime) {
        this.scheduleDepartTime = scheduleDepartTime;
    }
}
