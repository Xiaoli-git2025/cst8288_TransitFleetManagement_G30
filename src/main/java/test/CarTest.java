package test;

import business.builder.Car;
import business.builder.CarBuilder;
import business.builder.CarDirector;
import java.math.BigDecimal;

public class CarTest {
    public static void main(String[] args) {
        // Simulate front-end "add" operation: Get template through factory, then set properties dynamically
        System.out.println("=== Simulate front-end adding electric car ===");
        CarBuilder electricBuilder = CarDirector.createCarBuilder("Electronic");
        if (electricBuilder != null) {
            // Properties input from front-end form (simulation)
            Car electricCar = electricBuilder
                    .withVehicleNumber("E-12345") // License plate from front-end input
                    //.withEngine(201) // Engine selected from front-end
                    .withCapacity(50) // Capacity input from front-end
                    .withConsumptionRate(new BigDecimal("0.8")) // Energy consumption from front-end
                    .withMaxPassenger(5) // Passenger capacity from front-end
                    .withRouteId(101) // Route selected from front-end
                    .build();
            System.out.println("New electric car information:");
            System.out.println("License plate: " + electricCar.getVehicleNumber());
            System.out.println("Fuel type: " + electricCar.getFuelType());
        }

        // Simulate front-end "modify" operation: Update properties based on existing object
        System.out.println("\n=== Simulate front-end modifying vehicle ===");
        Car oldCar = new CarBuilder()
                .withVehicleNumber("D-54321")
                .withFuelType("Diesel")
                .withMaxPassenger(4)
                .build();
        System.out.println("Passenger capacity before modification: " + oldCar.getMaxPassenger());

        // Front-end modification operation (update passenger capacity)
        Car updatedCar = new CarBuilder(oldCar) // Create builder based on old object
                .withMaxPassenger(7) // Update passenger capacity from front-end
                .build();
        System.out.println("Passenger capacity after modification: " + updatedCar.getMaxPassenger());

        // Test invalid type
        System.out.println("\n=== Test invalid car type ===");
        CarBuilder invalidBuilder = CarDirector.createCarBuilder("Gasoline");
        System.out.println("Invalid car type handling: " + (invalidBuilder == null ? "Creation failed (as expected)" : "Creation succeeded (unexpected)"));
    }
}
