package model;

/**
 * VehicleDTO is a Data Transfer Object for the 'Vehicle' table.
 * It represents the vehicles used in the transit system, including performance and capacity details.
 */
public class VehicleDTO {

    // Unique identifier for the vehicle (Primary Key, auto-incremented)
    private int vehicleId;

    // Unique vehicle number or license plate
    private String vehicleNumber;

    // Fuel or energy consumption rate (e.g., liters/km)
    private java.math.BigDecimal consumptionRate;

    // Maximum number of passengers the vehicle can carry
    private int maxPassenger;

    // Type of fuel used (e.g., Diesel, Electric)
    private String fuelType;

    // Foreign key referring to the assigned route
    private int routeId;

    // Total capacity of the vehicle (could represent seats or weight limit)
    private int capacity;
    
    private int componentId;

    // Constructors
    public VehicleDTO() {}

    public VehicleDTO(int vehicleId, String vehicleNumber, java.math.BigDecimal consumptionRate,
                      int maxPassenger, String fuelType, int routeId, int capacity, int componentId) {
        this.vehicleId = vehicleId;
        this.vehicleNumber = vehicleNumber;
        this.consumptionRate = consumptionRate;
        this.maxPassenger = maxPassenger;
        this.fuelType = fuelType;
        this.routeId = routeId;
        this.capacity = capacity;
        this.componentId = componentId;
    }

    // Getters and Setters
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public java.math.BigDecimal getConsumptionRate() {
        return consumptionRate;
    }

    public void setConsumptionRate(java.math.BigDecimal consumptionRate) {
        this.consumptionRate = consumptionRate;
    }

    public int getMaxPassenger() {
        return maxPassenger;
    }

    public void setMaxPassenger(int maxPassenger) {
        this.maxPassenger = maxPassenger;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getComponentId(){
        return componentId;
    }
    public void setComponentId(int componentId){
        this.componentId = componentId;
    }
    
}
