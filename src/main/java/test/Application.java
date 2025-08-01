package test;

import business.builder.VehicleBusinessLogic;
import business.builder.Car;
import business.builder.CarBuilder;
import business.builder.CarDirector;
import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        VehicleBusinessLogic vehicleService = new VehicleBusinessLogic();
        
        // Create a new diesel vehicle using Simple Factory and Builder patterns
        boolean created = vehicleService.createVehicle(
            "Diesel",
            "VH-2023-001",
            new BigDecimal("5.2"),
           2,
            2,
            60,
            2
        );
        
        if (created) {
            System.out.println("Vehicle created successfully!");
        }
        
        // Retrieve and update a vehicle
        Car existingCar = vehicleService.convertToCar(vehicleService.getVehicleById(1));
        if (existingCar != null) {
            // Create a builder from existing car and modify it
            Car updatedCar = new CarBuilder(existingCar)
                .withMaxPassenger(55)  // Increase passenger capacity
                .build();
            
            boolean updated = vehicleService.updateVehicle(1, updatedCar);
            if (updated) {
                System.out.println("Vehicle updated successfully!");
            }
        }
    }
}