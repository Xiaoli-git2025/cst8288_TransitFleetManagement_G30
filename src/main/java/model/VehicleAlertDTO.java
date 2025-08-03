/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Student ID: 040469755
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
package model;

/**
 * Data Transfer Object (DTO) representing vehicle alert information.
 * This class encapsulates the essential details of a vehicle alert including
 * the associated user and vehicle identifiers, alert type, and description.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class VehicleAlertDTO {
    private int user_id;
    private int vehicle_id;
    private String alert_type;
    private String description;
    
    /**
     * Default constructor for VehicleAlertDTO.
     * Creates a new instance with uninitialized field values.
     */
    public VehicleAlertDTO() {
    }

    /**
     * Gets the user ID associated with this vehicle alert.
     * 
     * @return the user ID as an integer
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * Gets the vehicle ID associated with this alert.
     * 
     * @return the vehicle ID as an integer
     */
    public int getVehicleId() {
        return vehicle_id;
    }

    /**
     * Gets the type of the alert.
     * 
     * @return the alert type as a String
     */
    public String getAlertType() {
        return alert_type;
    }

    /**
     * Gets the description of the alert.
     * 
     * @return the alert description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the user ID associated with this vehicle alert.
     * 
     * @param user_id the user ID to set
     */
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Sets the vehicle ID associated with this alert.
     * 
     * @param vehicle_id the vehicle ID to set
     */
    public void setVehicleId(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    /**
     * Sets the type of the alert.
     * 
     * @param alert_type the alert type to set
     */
    public void setAlertType(String alert_type) {
        this.alert_type = alert_type;
    }

    /**
     * Sets the description of the alert.
     * 
     * @param description the alert description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}

