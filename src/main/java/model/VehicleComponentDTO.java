package model;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
/**
 * VehicleComponentDTO is a Data Transfer Object for the 'VehicleComponent' table.
 * It represents components installed on vehicles, including usage and threshold hours.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class VehicleComponentDTO {

    // Unique ID for each component record (Primary Key, auto-incremented)
    private int componentId;

    // Name of the component (e.g., engine, battery, etc.)
    private String componentName;

    // Foreign key: ID of the vehicle this component belongs to
    private int vehicleId;

    // Number of hours this component has been used
    private int usedHour;

    // Threshold in hours before the component requires maintenance or replacement
    private int thresholdHour;

    // Constructors
    public VehicleComponentDTO() {} 
    /**
     * Constructs a VehicleComponentDTO with specified values.
     *
     * @param componentId   the unique identifier of the component
     * @param componentName the name of the component
     * @param vehicleId     the ID of the vehicle to which the component belongs
     * @param usedHour      the number of hours the component has been used
     * @param thresholdHour the threshold hour indicating when maintenance is needed
     */ public VehicleComponentDTO(int componentId, String componentName, int vehicleId,
                                int usedHour, int thresholdHour) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.vehicleId = vehicleId;
        this.usedHour = usedHour;
        this.thresholdHour = thresholdHour;
    }

    /**
     * Gets the component ID.
     *
     * @return the component ID
     */
    public int getComponentId() {
        return componentId;
    }

    /**
     * Sets the component ID.
     *
     * @param componentId the component ID to set
     */
    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    /**
     * Gets the component name.
     *
     * @return the component name
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * Sets the component name.
     *
     * @param componentName the component name to set
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * Gets the vehicle ID associated with the component.
     *
     * @return the vehicle ID
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID associated with the component.
     *
     * @param vehicleId the vehicle ID to set
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the number of hours the component has been used.
     *
     * @return the used hours
     */
    public int getUsedHour() {
        return usedHour;
    }

    /**
     * Sets the number of hours the component has been used.
     *
     * @param usedHour the used hours to set
     */
    public void setUsedHour(int usedHour) {
        this.usedHour = usedHour;
    }

    /**
     * Gets the threshold hour for maintenance.
     *
     * @return the threshold hour
     */
    public int getThresholdHour() {
        return thresholdHour;
    }

    /**
     * Sets the threshold hour for maintenance.
     *
     * @param thresholdHour the threshold hour to set
     */
    public void setThresholdHour(int thresholdHour) {
        this.thresholdHour = thresholdHour;
    }
}
   