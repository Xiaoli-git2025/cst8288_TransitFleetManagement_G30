package model;

/**
 * RouteScheduleDTO is a Data Transfer Object for the 'RouteSchedule' table.
 * It represents a scheduled stop for a specific route at a particular station,
 * including arrival and departure times.
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

    public RouteScheduleDTO(int scheduleId, int routeId, int stationId,
                            int scheduleNumber, java.sql.Time scheduleArriveTime, java.sql.Time scheduleDepartTime) {
        this.scheduleId = scheduleId;
        this.routeId = routeId;
        this.stationId = stationId;
        this.scheduleNumber = scheduleNumber;
        this.scheduleArriveTime = scheduleArriveTime;
        this.scheduleDepartTime = scheduleDepartTime;
    }

    // Getters and Setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getScheduleNumber() {
        return scheduleNumber;
    }

    public void setScheduleNumber(int scheduleNumber) {
        this.scheduleNumber = scheduleNumber;
    }

    public java.sql.Time getScheduleArriveTime() {
        return scheduleArriveTime;
    }

    public void setScheduleArriveTime(java.sql.Time scheduleArriveTime) {
        this.scheduleArriveTime = scheduleArriveTime;
    }

    public java.sql.Time getScheduleDepartTime() {
        return scheduleDepartTime;
    }

    public void setScheduleDepartTime(java.sql.Time scheduleDepartTime) {
        this.scheduleDepartTime = scheduleDepartTime;
    }
}
