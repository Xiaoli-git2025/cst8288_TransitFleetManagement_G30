package business.builder;
import java.math.BigDecimal;
import model.VehicleDTO;

/**
 * This is the base of builder
 * @author shano
 */
public class Car {

    private VehicleDTO vehicle;
    private String vehicleNumber;
    private BigDecimal consumptionRate;
    private int maxPassenger;
    private String fuelType;
    private int routeId;
    private int capacity;
    //private int vehicleId;
    
    public Car(String vehicleNumber, BigDecimal consumptionRate, int maxPassenger, String fuelType, int routeId, int capacity) {
        this.vehicleNumber = vehicleNumber;
        this.consumptionRate = consumptionRate;
        this.maxPassenger = maxPassenger;
        this.fuelType = fuelType;
        this.routeId = routeId;
        this.capacity = capacity;
        this.vehicle = new VehicleDTO(); 
    }

    /*
    public int getVehicleId() {
        return vehicleId;
    }
    */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public BigDecimal getConsumptionRate() {
        return consumptionRate;
    }

    public int getMaxPassenger() {
        return maxPassenger;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getRouteId() {
        return routeId;
    }

    public int getCapacity() {
        return capacity;
    }

    /*
    public void setVehicleId(int vehicleId) {

        this.vehicleId = vehicle.getVehicleId();
    }
    */
    public void setVehicleNumber() {
        this.vehicleNumber = vehicle.getVehicleNumber();
    }

    public void setConsumptionRate() {
        this.consumptionRate = vehicle.getConsumptionRate();
    }

    public void setMaxPassenger() {
        this.maxPassenger = vehicle.getMaxPassenger();
    }

    public void setFuelType() {
        this.fuelType = vehicle.getFuelType();
    }

    public void setRouteId() {
        this.routeId = vehicle.getRouteId();
    }

    public void setCapacity() {
        this.capacity = vehicle.getCapacity();
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    public CarBuilder build() {
        return new CarBuilder();
    }
}
