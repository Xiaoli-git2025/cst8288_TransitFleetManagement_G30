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
 * business logic for fuel consumption
 * @author Xiaoli He
 */
public class FuelConsumptionBusinessLogic {
    /**
     * fuel consumption dao
     */
    FuelConsumptionDAO fuelDao = null;
    /**
     * vehicle dao
     */
    VehicleDAO vehicleDao = null;
    
    /**
     * constructor, new dao
     */
    public FuelConsumptionBusinessLogic(){
        fuelDao = new FuelConsumptionDAO();
        vehicleDao = new VehicleDAO();
    }
    
    /**
     * get fuel consumption by vehicle 
     * @param vehicle_id vehicle id
     * @return fuel consumptions
     * @throws SQLException SQLException
     */
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
    
    /**
     * log new fuel consumption
     * @param fuel new fuel consumption
     * @return if add successfully
     */
    public boolean LogFuelConsumption(FuelConsumptionDTO fuel){
        return fuelDao.add(fuel);
    }
    
    /**
     * update fuel consumption
     * @param fuel fuel consumption
     * @return if update successfully
     */
    public boolean UpdateFuelConsumption(FuelConsumptionDTO fuel){
        return fuelDao.update(fuel);
    }
    
    /**
     * get fuel consumption by id
     * @param fuel_id fuel consumption id
     * @return fuel consumption object
     */
    public FuelConsumptionDTO getFuelConsumptionById(int fuel_id){
        return fuelDao.getById(fuel_id);
    }
    
    /**
     * delete fuel consumption
     * @param fc_id fuel consumption id
     * @return if delete successfully
     */
    public boolean deleteFuelConsumptionById(int fc_id){
        return fuelDao.delete(fc_id);
    }
    
    /**
     * get vehicle by fuel consumption
     * @param fc_id fuel consumption id
     * @return vehicle
     */
    public VehicleDTO getVehicleByFCId(int fc_id){
        FuelConsumptionDTO fuel = fuelDao.getById(fc_id);
        int vehicle_id = fuel.getVehicleId();
        return vehicleDao.getById(vehicle_id);
    }
}
