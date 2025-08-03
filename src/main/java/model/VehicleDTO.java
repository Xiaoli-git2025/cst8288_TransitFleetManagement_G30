/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Student ID: 040469755
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
package model;

/**
 * VehicleDTO is a Data Transfer Object for the 'Vehicle' table.
 * It represents the vehicles used in the transit system, including performance and capacity details.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
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
    
    // Constructors
    public VehicleDTO() {}


    /**
     * Constructs a VehicleDTO with all properties.
     *
     * @param vehicleId        the unique identifier for the vehicle
     * @param vehicleNumber    the registration number or identifier of the vehicle
     * @param consumptionRate  the fuel consumption rate of the vehicle
     * @param maxPassenger     the maximum number of passengers the vehicle can carry
     * @param fuelType         the type of fuel used by the vehicle (e.g., gasoline, diesel)
     * @param routeId          the ID of the route assigned to the vehicle
     * @param capacity         the load capacity of the vehicle
     */
    public VehicleDTO(int vehicleId, String vehicleNumber, java.math.BigDecimal consumptionRate,
                      int maxPassenger, String fuelType, int routeId, int capacity) {
        this.vehicleId = vehicleId;
        this.vehicleNumber = vehicleNumber;
        this.consumptionRate = consumptionRate;
        this.maxPassenger = maxPassenger;
        this.fuelType = fuelType;
        this.routeId = routeId;
        this.capacity = capacity;
    }

    /**
     * Gets the vehicle ID.
     *
     * @return the vehicle ID
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID.
     *
     * @param vehicleId the vehicle ID to set
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the vehicle number.
     *
     * @return the vehicle number
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * Sets the vehicle number.
     *
     * @param vehicleNumber the vehicle number to set
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * Gets the consumption rate of the vehicle.
     *
     * @return the consumption rate
     */
    public java.math.BigDecimal getConsumptionRate() {
        return consumptionRate;
    }

    /**
     * Sets the consumption rate of the vehicle.
     *
     * @param consumptionRate the consumption rate to set
     */
    public void setConsumptionRate(java.math.BigDecimal consumptionRate) {
        this.consumptionRate = consumptionRate;
    }

    /**
     * Gets the maximum passenger capacity.
     *
     * @return the maximum number of passengers
     */
    public int getMaxPassenger() {
        return maxPassenger;
    }

    /**
     * Sets the maximum passenger capacity.
     *
     * @param maxPassenger the maximum number of passengers to set
     */
    public void setMaxPassenger(int maxPassenger) {
        this.maxPassenger = maxPassenger;
    }

    /**
     * Gets the fuel type used by the vehicle.
     *
     * @return the fuel type
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Sets the fuel type used by the vehicle.
     *
     * @param fuelType the fuel type to set
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * Gets the route ID assigned to the vehicle.
     *
     * @return the route ID
     */
    public int getRouteId() {
        return routeId;
    }

    /**
     * Sets the route ID assigned to the vehicle.
     *
     * @param routeId the route ID to set
     */
    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    /**
     * Gets the load capacity of the vehicle.
     *
     * @return the load capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the load capacity of the vehicle.
     *
     * @param capacity the load capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

