/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import model.FuelConsumptionDTO;
import dao.FuelConsumptionDAO;
import dao.VehicleDAO;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import model.VehicleDTO;
/**
 *
 * @author Administrator
 */
public class FuelConsumptionBusinessLogic {
    FuelConsumptionDAO fuelDao = null;
    VehicleDAO vehicleDao = null;
    
    public FuelConsumptionBusinessLogic(){
        fuelDao = new FuelConsumptionDAO();
        vehicleDao = new VehicleDAO();
    }
    
    public List<FuelConsumptionDTO> getFuelConsumptionsByVehicleId(int vehicle_id)throws SQLException{
        List<FuelConsumptionDTO> all = fuelDao.getAll();
        Iterator<FuelConsumptionDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            FuelConsumptionDTO fuel = iterator.next();
            int vid = fuel.getVehicleId();
            if (vid != vehicle_id) {
                iterator.remove();
            }
        }
        all.sort(Comparator.comparing(FuelConsumptionDTO::getFcId));
        return all;
    }
    
    public boolean LogFuelConsumption(FuelConsumptionDTO fuel){
        return fuelDao.add(fuel);
    }
    
    public boolean UpdateFuelConsumption(FuelConsumptionDTO fuel){
        return fuelDao.update(fuel);
    }
    
    public FuelConsumptionDTO getFuelConsumptionById(int fuel_id){
        return fuelDao.getById(fuel_id);
    }
    
    public boolean deleteFuelConsumptionById(int fc_id){
        return fuelDao.delete(fc_id);
    }
    
    public VehicleDTO getVehicleByFCId(int fc_id){
        FuelConsumptionDTO fuel = fuelDao.getById(fc_id);
        int vehicle_id = fuel.getVehicleId();
        return vehicleDao.getById(vehicle_id);
    }
}
