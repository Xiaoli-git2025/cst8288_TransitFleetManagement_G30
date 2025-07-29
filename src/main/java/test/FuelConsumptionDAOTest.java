package test;

import model.FuelConsumptionDTO;
import model.FuelConsumptionDTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class FuelConsumptionDAOTest {

    public static void main(String[] args) {
        // Create a test FuelConsumptionDTO object
        FuelConsumptionDTO fuelRecord = new FuelConsumptionDTO();

        // Set values
        fuelRecord.setFcId(1);
        fuelRecord.setVehicleId(101);
        fuelRecord.setDate(Date.valueOf("2025-07-28"));
        fuelRecord.setMilesTraveled(new BigDecimal("123.45"));
        fuelRecord.setUnitPrice(new BigDecimal("1.89"));

        // Print values to verify
        System.out.println("Fuel Consumption Record:");
        System.out.println("  fcId: " + fuelRecord.getFcId());
        System.out.println("  vehicleId: " + fuelRecord.getVehicleId());
        System.out.println("  date: " + fuelRecord.getDate());
        System.out.println("  milesTraveled: " + fuelRecord.getMilesTraveled());
        System.out.println("  unitPrice: $" + fuelRecord.getUnitPrice());
    }
}
