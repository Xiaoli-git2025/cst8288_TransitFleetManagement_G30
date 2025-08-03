/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.FuelCostStrategy;

/**
 * Hybrid Fuel Cost
 * @author Xiaoli He
 */
public class HybridFuelCostStrategy  implements FuelCostStrategy {
    /**
     * calculate cost
     * @param unitPrice unit price
     * @param milesTraveled miles traveled
     * @return cost
     */
    public java.math.BigDecimal calculateCost(java.math.BigDecimal unitPrice, java.math.BigDecimal milesTraveled) {
        return unitPrice.multiply(milesTraveled).multiply(new java.math.BigDecimal("0.08"));
    }
}
