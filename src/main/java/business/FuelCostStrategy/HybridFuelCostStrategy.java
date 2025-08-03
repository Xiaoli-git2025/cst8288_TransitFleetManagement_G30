/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.FuelCostStrategy;

/**
 *
 * @author Administrator
 */
public class HybridFuelCostStrategy  implements FuelCostStrategy {
    public java.math.BigDecimal calculateCost(java.math.BigDecimal unitPrice, java.math.BigDecimal milesTraveled) {
        return unitPrice.multiply(milesTraveled).multiply(new java.math.BigDecimal("0.08"));
    }
}
