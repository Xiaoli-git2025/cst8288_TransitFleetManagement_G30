package model;

/**
 * VehicleComponentDTO is a Data Transfer Object for the 'VehicleComponent' table.
 * It represents components installed on vehicles, including usage and threshold hours.
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

    public VehicleComponentDTO(int componentId, String componentName, int vehicleId,
                                int usedHour, int thresholdHour) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.vehicleId = vehicleId;
        this.usedHour = usedHour;
        this.thresholdHour = thresholdHour;
    }

    // Getters and Setters
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getUsedHour() {
        return usedHour;
    }

    public void setUsedHour(int usedHour) {
        this.usedHour = usedHour;
    }

    public int getThresholdHour() {
        return thresholdHour;
    }

    public void setThresholdHour(int thresholdHour) {
        this.thresholdHour = thresholdHour;
    }
}
