/* File: CarDirector.java
 * Author: Shan Cai
 * Date: 2025/7/29
 * Description: Director to assemable car
 */
package business.builder;

/**
 * The combination of simple factory and builder pattern
 * to create different type of car
 * @author Shan Cai
 */
public class CarDirector {

    /**
     * Create a car instance with different type, diesel, electronic, hybrid
     * @param type the car type string
     * @return car
     */
    public static Car createCar(String type) {

        CarBuilder builder = new CarBuilder();
        switch (type) {
            case "Diesel":
                return builder.addCarBreak("carbreakgas")
                        .addEngine("enginegas")
                        .build();
            case "Electronic":
                return builder.addCarBreak("carbreakelectronic")
                        .addEngine("engineelectronic")
                        .build();
            case "Hybrid":
                return builder.addCarBreak("carbreakhybrid")
                        .addEngine("enginehybrid")
                        .build();
            default:
                System.out.println("There are only three type of car");
                return null;
        }
    }
}
