package business.builder;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/

/**
 * Director for creating different type car
 * It plays the role of the Director in the Builder Pattern
 * a simplified form of the Simple Factory Pattern by selecting
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
public class CarDirector {
    
    /**
     * Returns a carBuidler pre-configured with a fuel type,
     * based on the provided string input.
     *
     * @param type the type of fuel ("Diesel", "Electronic", or "Hybrid")
     * @return car builder with the selection 
     */
    public static CarBuilder createCarBuilder(String type) {
        CarBuilder builder = new CarBuilder();
        switch (type) {
            case "Diesel":
                return builder.withFuelType("Diesel");
            case "Electronic":
                return builder.withFuelType("Electronic");
            case "Hybrid":
                return builder.withFuelType("Hybrid");
            default:
                System.out.println("only three types：Diesel, Electronic, Hybrid");
                return null;
        }
    }
}