/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.FuelCostStrategy;
import dao.VehicleDAO;
import model.VehicleDTO;
/**
 *
 * @author Administrator
 */
public class FuelCostStrategyFactory {
    
    public static FuelCostStrategy getStrategyByVehicleId(int vehicleId) {
        VehicleDAO v_dao = new VehicleDAO();
        String fuel_type = v_dao.getById(vehicleId).getFuelType();
        if (fuel_type.equals("Diesel")){
            return new DieselFuelCostStrategy();
        } else if (fuel_type.equals("Electric")) {
            return new ElectricFuelCostStrategy();
        } else {
            return new HybridFuelCostStrategy();
        }
    }
}
