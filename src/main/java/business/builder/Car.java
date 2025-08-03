package business.builder;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
import java.math.BigDecimal;
import model.VehicleDTO;

/**
 * This is the base of builder
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class Car {

    private VehicleDTO vehicle;
    private String vehicleNumber;
    private BigDecimal consumptionRate;
    private int maxPassenger;
    private String fuelType;
    private int routeId;
    private int capacity;

    /**
     * Constructs a new Car object with the provided properties.
     *
     * @param vehicleNumber the vehicle's unique number
     * @param consumptionRate the vehicle's fuel consumption rate
     * @param maxPassenger the maximum number of passengers
     * @param fuelType the type of fuel used (e.g., gas, diesel)
     * @param routeId the ID of the assigned route
     * @param capacity the capacity of the vehicle
     */
    public Car(String vehicleNumber, BigDecimal consumptionRate, int maxPassenger, String fuelType, int routeId, int capacity) {
        this.vehicleNumber = vehicleNumber;
        this.consumptionRate = consumptionRate;
        this.maxPassenger = maxPassenger;
        this.fuelType = fuelType;
        this.routeId = routeId;
        this.capacity = capacity;
        this.vehicle = new VehicleDTO(); 
    }

     /**
     * Gets the vehicle number.
     * @return the vehicle number
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * Gets the fuel consumption rate.
     * @return the consumption rate
     */
    public BigDecimal getConsumptionRate() {
        return consumptionRate;
    }

    /**
     * Gets the maximum number of passengers.
     * @return the maximum passenger count
     */
    public int getMaxPassenger() {
        return maxPassenger;
    }

    /**
     * Gets the fuel type.
     * @return the fuel type
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Gets the assigned route ID.
     * @return the route ID
     */
    public int getRouteId() {
        return routeId;
    }

    /**
     * Gets the capacity of the vehicle.
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the vehicle number from the internal VehicleDTO object.
     */
    public void setVehicleNumber() {
        this.vehicleNumber = vehicle.getVehicleNumber();
    }

    /**
     * Sets the consumption rate from the internal VehicleDTO object.
     */
    public void setConsumptionRate() {
        this.consumptionRate = vehicle.getConsumptionRate();
    }

    /**
     * Sets the maximum passenger count from the internal VehicleDTO object.
     */
    public void setMaxPassenger() {
        this.maxPassenger = vehicle.getMaxPassenger();
    }

    /**
     * Sets the fuel type from the internal VehicleDTO object.
     */
    public void setFuelType() {
        this.fuelType = vehicle.getFuelType();
    }

    /**
     * Sets the route ID from the internal VehicleDTO object.
     */
    public void setRouteId() {
        this.routeId = vehicle.getRouteId();
    }

    /**
     * Sets the capacity from the internal VehicleDTO object.
     */
    public void setCapacity() {
        this.capacity = vehicle.getCapacity();
    }

    /**
     * Sets the internal VehicleDTO used to populate fields.
     *
     * @param vehicle the VehicleDTO object
     */
    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Creates and returns a new CarBuilder instance.
     *
     * @return a new CarBuilder
     */
    public CarBuilder build() {
        return new CarBuilder();
    }
}
