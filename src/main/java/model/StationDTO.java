package model;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
/**
 * StationDTO is a Data Transfer Object for the 'Station' table.
 * It encapsulates the basic data about a station, including its ID and name.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class StationDTO {

    // Unique identifier for each station (Primary Key, auto-incremented)
    private int stationId;

    // Name of the station (e.g., "Central Station", "Bayview Terminal")
    private String stationName;

    // Constructors
    public StationDTO() {
    }

    public StationDTO(int stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }

    // Getters and Setters
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}