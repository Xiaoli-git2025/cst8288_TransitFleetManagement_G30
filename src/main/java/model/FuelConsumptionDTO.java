package model;

/**
 * FuelConsumptionDTO is a Data Transfer Object for the 'FuelConsumption' table.
 * It represents fuel usage records for vehicles, including distance traveled and fuel cost.
 */
public class FuelConsumptionDTO {

    // Unique identifier for each fuel consumption record (Primary Key, auto-incremented)
    private int fcId;

    // Foreign key referencing the vehicle that this consumption record belongs to
    private int vehicleId;

    // Date of the fuel consumption record
    private java.sql.Date date;

    // Total miles traveled on this date
    private java.math.BigDecimal milesTraveled;

    // Unit price of the fuel (e.g., per liter or gallon)
    private java.math.BigDecimal unitPrice;
    
    private java.math.BigDecimal cost;

    // Constructors
    public FuelConsumptionDTO() {}

    public FuelConsumptionDTO(int fcId, int vehicleId, java.sql.Date date,
                              java.math.BigDecimal milesTraveled, java.math.BigDecimal unitPrice) {
        this.fcId = fcId;
        this.vehicleId = vehicleId;
        this.date = date;
        this.milesTraveled = milesTraveled;
        this.unitPrice = unitPrice;
    }

    public java.math.BigDecimal getCost(){
        return cost;
    }
    
    public void setCost(java.math.BigDecimal cost){
        this.cost = cost;
    }
    
    // Getters and Setters
    public int getFcId() {
        return fcId;
    }

    public void setFcId(int fcId) {
        this.fcId = fcId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public java.math.BigDecimal getMilesTraveled() {
        return milesTraveled;
    }

    public void setMilesTraveled(java.math.BigDecimal milesTraveled) {
        this.milesTraveled = milesTraveled;
    }

    public java.math.BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(java.math.BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
