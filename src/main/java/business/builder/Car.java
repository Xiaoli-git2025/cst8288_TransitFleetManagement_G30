/* File: DataSource.java
 * Author: Shan Cai
 * Date: 2025/7/29
 * Description: base builder
 */
package business.builder;

/**
 * This is the base of builder
 * @author shano
 */
public class Car {
    
    /**This is the component of a car*/
    private String carBreak;
    /**This is the component of a car*/
    private String engine;

    
    /**
     * Constructor for CarBuilder
     * @param carBreak
     * @param engine
     */
    public Car(String carBreak, String engine){
        
        this.carBreak= carBreak;
        this.engine = engine;
    }
    
    /**
     * Factory method to create CarBuilder;
     * @return CarBuilder object of CarBuilder;
     */
    public static CarBuilder builder(){
        return new CarBuilder();
    }
}
