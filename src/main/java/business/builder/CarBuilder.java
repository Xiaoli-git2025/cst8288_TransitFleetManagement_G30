package business.builder;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
import java.math.BigDecimal;
/**
 * Builder class for constructing car
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class CarBuilder {
    private String vehicleNumber;
    private BigDecimal consumptionRate;
    private int maxPassenger;
    private String fuelType;
    private int routeId;
    private int capacity;
    /**
     * Default constructor for CarBuilder.
     */
    public CarBuilder() {}
    /**
     * Initializes the builder with values from an existing {@code Car} object.
     *
     * @param car the object whose properties are copied
     */
    public CarBuilder(Car car) {
        this.vehicleNumber = car.getVehicleNumber();
        this.consumptionRate = car.getConsumptionRate();
        this.maxPassenger = car.getMaxPassenger();
        this.fuelType = car.getFuelType();
        this.routeId = car.getRouteId();
        this.capacity = car.getCapacity();
    }
    /**
     * adds the vehicle number.
     *
     * @param vehicleNumber the vehicle number
     * @return the current instance
     */
    public CarBuilder withVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }
    /**
     * adds the consumption rate.
     *
     * @param consumptionRate the fuel consumption rate
     * @return the current instance
     */
    public CarBuilder withConsumptionRate(BigDecimal consumptionRate) {
        this.consumptionRate = consumptionRate;
        return this;
    }
    /**
     * adds the maximum number of passengers.
     *
     * @param maxPassenger the passenger capacity
     * @return the current instance
     */
    public CarBuilder withMaxPassenger(int maxPassenger) {
        this.maxPassenger = maxPassenger;
        return this;
    }
    /**
     * adds the fuel type.
     *
     * @param fuelType the fuel type 
     * @return the current instance
     */
    public CarBuilder withFuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }
    /**
     * adds the route ID.
     *
     * @param routeId the route identifier
     * @return the current instance
     */
    public CarBuilder withRouteId(int routeId) {
        this.routeId = routeId;
        return this;
    }
    /**
     * adds the vehicle capacity.
     *
     * @param capacity the vehicle's load or seating capacity
     * @return the current instance
     */
    public CarBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }
    /**
     * Builds and returns a new object with the specified properties.
     *
     * @return a new  instance
     */
    public Car build() {
        return new Car(
            vehicleNumber,
            consumptionRate,
            maxPassenger,
            fuelType,
            routeId,
            capacity);
    }
}