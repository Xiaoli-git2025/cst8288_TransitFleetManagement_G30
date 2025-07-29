/* File: DataSource.java
 * Author: Shan Cai
 * Date: 2025/7/29
 * Description: car builder
 */
package business.builder;

/**
 * Car builder with adding component and return a car object
 * @author Shan Cai
 */
public class CarBuilder {
    
    /**This is the component of a car*/
    private String carBreak;
    /**This is the component of a car*/
    private String engine;
    
    /**
     * method to add break to a car
     * @param carBreak is the component of a car
     * @return this is the component of a car
     */
    public CarBuilder addCarBreak(String carBreak){
        this.carBreak = carBreak;
        return this;
    }
    /**
     * method to add engine
     * @param engine is the component of a car
     * @return this engine
     */
    public CarBuilder addEngine(String engine){
        this.engine = engine;
        return this;
    }

    /**
     * build method to return a car with components
     * @return Car object
     */
    public Car build(){
        return new Car(carBreak,engine);
    }
}
