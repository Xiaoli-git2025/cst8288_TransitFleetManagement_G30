/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import dao.*;
import model.*;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrator
 */
public class AlertBusinessLogic {
    /**
     * DAO instance variable for Title
     */
    private VehicleDAO vehicleDao = null;
    private MaintenanceAlertDAO maintAlertDao = null;
    private MaintenanceScheduleDAO scheduleDao = null;
    private VehicleComponentDAO componentDao = null;
    private AlertDAO alertDao = null;
    private UserDAO userDao = null;
    /**
     * Constructor for BusinessLogic
     */
    public AlertBusinessLogic() {
        vehicleDao = new VehicleDAO();
        maintAlertDao = new MaintenanceAlertDAO();
        scheduleDao = new MaintenanceScheduleDAO();
        componentDao = new VehicleComponentDAO();
        alertDao = new AlertDAO();
        userDao = new UserDAO();
    }

    /**
     * get all objects
     * @return all titles
     * @throws SQLException throw exception
     */
    public List<VehicleDTO> getVehiclesByUserId(int user_id) throws SQLException {
        List<VehicleDTO> all = vehicleDao.getAll();
        int route_id = userDao.getById(user_id).getRoute_id();
        Iterator<VehicleDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            VehicleDTO vehicle = iterator.next();
            int routeid = vehicle.getRouteId();

            if (routeid != route_id) {
                iterator.remove();
            }
        }
        return all;
    }
    
    public String getAlertType(int alert_id){
        return alertDao.getById(alert_id).getAlertType();
    }
    
    public String getComponentNameByComponentId(int component_id){
        return componentDao.getById(component_id).getComponentName();
    }
    
    public List<MaintenanceAlertDTO> getMaintAlertByUserId_VehicleId(int v_id, int u_id) throws SQLException {
        List<MaintenanceAlertDTO> all =  maintAlertDao.getAll();
        Iterator<MaintenanceAlertDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            MaintenanceAlertDTO alert = iterator.next();
            int componentId = alert.getComponentId();
            int vehicleId = componentDao.getById(componentId).getVehicleId();

            if (vehicleId != v_id || alert.getReporterId() != u_id) {
                iterator.remove();  // ✅ safe removal
            }
        }
        return all;
    }
    
    public boolean deleteMaintAlert(int alert_id){
        return maintAlertDao.delete(alert_id);
    }
    
    public int getVehicleIdByAlertId(int alert_id){
        return componentDao.getById(maintAlertDao.getById(alert_id).getComponentId()).getVehicleId();
    }
    
    public MaintenanceAlertDTO getAlertByMaintAlertId(int maintalert_id){
        return maintAlertDao.getById(maintalert_id);
    }
    
    public List<VehicleComponentDTO> getComponentsByVehicleId(int vehicle_id)throws SQLException {
        List<VehicleComponentDTO> all = componentDao.getAll();
        Iterator<VehicleComponentDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            VehicleComponentDTO comp = iterator.next();
            int vehicleid = comp.getVehicleId();
            if (vehicleid != vehicle_id) {
                iterator.remove();
            }
        }
        return all;
    }
    
    public VehicleDTO getVehicleByVehicleId(int vehicle_id){
        return vehicleDao.getById(vehicle_id);
    }
    
    public List<AlertDTO> getAllAlertTypes()throws SQLException {
        return alertDao.getAll();
    }
    
    public boolean addMaintAlert(MaintenanceAlertDTO malert){
        return maintAlertDao.add(malert);
    }
    
    public boolean updateMaintAlert(MaintenanceAlertDTO malert){
        return maintAlertDao.update(malert);
    }
    
    public String getMaintAlertReporterByMAlertId(int malert_id){
        return userDao.getById(maintAlertDao.getById(malert_id).getReporterId()).getName();
    }
    
    public List<MaintenanceAlertDTO> getActiveMaintAlert() throws SQLException {
        List<MaintenanceAlertDTO> all =  maintAlertDao.getAll();
        Iterator<MaintenanceAlertDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            MaintenanceAlertDTO alert = iterator.next();

            if (alert.isResolved()) {
                iterator.remove(); 
            }
        }
        return all;
    }
    
    public boolean addMaintSchedule(MaintenanceScheduleDTO schedule){
        return scheduleDao.add(schedule);
    }
    
    public List<MaintenanceScheduleDTO> getActiveSchedules() throws SQLException {
        List<MaintenanceScheduleDTO> all =  scheduleDao.getAll();
        Iterator<MaintenanceScheduleDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            MaintenanceScheduleDTO schedule = iterator.next();

            if (schedule.isCompleted()) {
                iterator.remove(); 
            }
        }
        return all;
    }
}
