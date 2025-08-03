/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package business.FuelCostStrategy;

/**
 * Strategy interface
 * @author Xiaoli He
 */
public interface FuelCostStrategy {
    /**
     * calculate cost
     * @param unitPrice unit price
     * @param milesTraveled miles traveled
     * @return cost
     */
    java.math.BigDecimal calculateCost(java.math.BigDecimal unitPrice, java.math.BigDecimal milesTraveled);
}
