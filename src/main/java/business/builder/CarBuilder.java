package business.builder;
import java.math.BigDecimal;

public class CarBuilder {
    private String vehicleNumber;
    private BigDecimal consumptionRate;
    private int maxPassenger;
    private String fuelType;
    private int routeId;
    private int capacity;

    public CarBuilder() {}

    public CarBuilder(Car car) {
        this.vehicleNumber = car.getVehicleNumber();
        this.consumptionRate = car.getConsumptionRate();
        this.maxPassenger = car.getMaxPassenger();
        this.fuelType = car.getFuelType();
        this.routeId = car.getRouteId();
        this.capacity = car.getCapacity();
    }

    public CarBuilder withVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public CarBuilder withConsumptionRate(BigDecimal consumptionRate) {
        this.consumptionRate = consumptionRate;
        return this;
    }

    public CarBuilder withMaxPassenger(int maxPassenger) {
        this.maxPassenger = maxPassenger;
        return this;
    }

    public CarBuilder withFuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public CarBuilder withRouteId(int routeId) {
        this.routeId = routeId;
        return this;
    }

    public CarBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

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