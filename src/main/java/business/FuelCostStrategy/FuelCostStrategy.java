/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package business.FuelCostStrategy;

/**
 *
 * @author Administrator
 */
public interface FuelCostStrategy {
    java.math.BigDecimal calculateCost(java.math.BigDecimal unitPrice, java.math.BigDecimal milesTraveled);
}
