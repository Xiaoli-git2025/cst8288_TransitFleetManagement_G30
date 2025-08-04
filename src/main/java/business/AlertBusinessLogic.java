/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import dao.*;
import model.*;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * business logic for maintenance alert and schedule
 * @author Xiaoli he
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
        all.sort(Comparator.comparing(VehicleDTO::getVehicleId));
        return all;
    }
    /**
     * get alert type
     * @param alert_id alert id
     * @return alert type
     */
    public String getAlertType(int alert_id){
        return alertDao.getById(alert_id).getAlertType();
    }
    
    /**
     * get component name by id
     * @param component_id component id
     * @return component name
     */
    public String getComponentNameByComponentId(int component_id){
        return componentDao.getById(component_id).getComponentName();
    }
    
    /**
     * get alerts by user id and vehicle id
     * @param v_id vehicle id
     * @param u_id user id
     * @return alerts
     * @throws SQLException exceptions
     */
    public List<MaintenanceAlertDTO> getMaintAlertByUserId_VehicleId(int v_id, int u_id) throws SQLException {
        List<MaintenanceAlertDTO> all =  maintAlertDao.getAll();
        Iterator<MaintenanceAlertDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            MaintenanceAlertDTO alert = iterator.next();
            int componentId = alert.getComponentId();
            int vehicleId = componentDao.getById(componentId).getVehicleId();

            if (vehicleId != v_id || alert.getReporterId() != u_id) {
                iterator.remove();  
            }
        }
        all.sort(Comparator.comparing(MaintenanceAlertDTO::getMaintenanceId));
        return all;
    }
    
    /**
     * get active alert
     * @param v_id vehicle id
     * @param u_id user id
     * @return active alerts
     * @throws SQLException exceptions
     */
    public List<MaintenanceAlertDTO> getActiveMaintAlertByUserId_VehicleId(int v_id, int u_id) throws SQLException {
        List<MaintenanceAlertDTO> all =  maintAlertDao.getAll();
        Iterator<MaintenanceAlertDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            MaintenanceAlertDTO alert = iterator.next();
            int componentId = alert.getComponentId();
            int vehicleId = componentDao.getById(componentId).getVehicleId();

            if (vehicleId != v_id || alert.getReporterId() != u_id || alert.isResolved()) {
                iterator.remove();
            }
        }
        all.sort(Comparator.comparing(MaintenanceAlertDTO::getMaintenanceId));
        return all;
    }
    
    /**
     * delete alert
     * @param alert_id alert id
     * @return if alert deleted successfully
     */
    public boolean deleteMaintAlert(int alert_id){
        return maintAlertDao.delete(alert_id);
    }
    
    /**
     * get vehicle id by alert id
     * @param alert_id alert id
     * @return vehicle id
     */
    public int getVehicleIdByAlertId(int alert_id){
        return componentDao.getById(maintAlertDao.getById(alert_id).getComponentId()).getVehicleId();
    }
    
    /**
     * get alert by id
     * @param maintalert_id alert id
     * @return alert
     */
    public MaintenanceAlertDTO getAlertByMaintAlertId(int maintalert_id){
        return maintAlertDao.getById(maintalert_id);
    }
    
    /**
     * get components by vehicle id
     * @param vehicle_id vehicle id
     * @return components
     * @throws SQLException exceptions
     */
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
        all.sort(Comparator.comparing(VehicleComponentDTO::getComponentId));
        return all;
    }
    
    /**
     * get vehicle by id
     * @param vehicle_id vehicle id
     * @return vehicle
     */
    public VehicleDTO getVehicleByVehicleId(int vehicle_id){
        return vehicleDao.getById(vehicle_id);
    }
    
    /**
     * get alert types
     * @return all alert types
     * @throws SQLException exceptions
     */
    public List<AlertDTO> getAllAlertTypes()throws SQLException {
        return alertDao.getAll();
    }
    
    /**
     * add alert
     * @param malert alert
     * @return if add successfully
     */
    public boolean addMaintAlert(MaintenanceAlertDTO malert){
        return maintAlertDao.add(malert);
    }
    
    /**
     * update alert
     * @param malert alert
     * @return if update successfully
     */
    public boolean updateMaintAlert(MaintenanceAlertDTO malert){
        return maintAlertDao.update(malert);
    }
    
    /**
     * get alert reporter
     * @param malert_id alert id
     * @return alert reporter
     */
    public String getMaintAlertReporterByMAlertId(int malert_id){
        return userDao.getById(maintAlertDao.getById(malert_id).getReporterId()).getName();
    }
    
    public void insertUsageAlert() throws SQLException{
        List<VehicleComponentDTO> components = componentDao.getAll();
        Iterator<VehicleComponentDTO> c_iterator = components.iterator();
        while (c_iterator.hasNext()) {
            VehicleComponentDTO component = c_iterator.next();
            if (component.getUsedHour()>component.getThresholdHour()) {
                MaintenanceAlertDTO alert = new MaintenanceAlertDTO();
                alert.setAlertId(1);
                alert.setComponentId(component.getComponentId());
                java.sql.Date log_date = java.sql.Date.valueOf(LocalDate.now());
                alert.setAlertDate(log_date);
                alert.setReporterId(1);
                alert.setResolved(false);
                maintAlertDao.add(alert);
                component.setUsedHour(0);
                componentDao.update(component);
            }
        }
    }
    /**
     * get active alert
     * @return active alerts
     * @throws SQLException exceptions
     */
    public List<MaintenanceAlertDTO> getActiveMaintAlert() throws SQLException {
        
        List<MaintenanceAlertDTO> all =  maintAlertDao.getAll();
        Iterator<MaintenanceAlertDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            MaintenanceAlertDTO alert = iterator.next();

            if (alert.isResolved()) {
                iterator.remove(); 
            }
        }
        all.sort(Comparator.comparing(MaintenanceAlertDTO::getMaintenanceId));
        return all;
    }
    
    /**
     * add maintenance schedule
     * @param schedule maintenance schedule
     * @return if add successfully
     */
    public boolean addMaintSchedule(MaintenanceScheduleDTO schedule){
        return scheduleDao.add(schedule);
    }
    
    /**
     * get active schedules
     * @return active schedules
     * @throws SQLException Exception
     */
    public List<MaintenanceScheduleDTO> getActiveSchedules() throws SQLException {
        List<MaintenanceScheduleDTO> all =  scheduleDao.getAll();
        Iterator<MaintenanceScheduleDTO> iterator = all.iterator();
        while (iterator.hasNext()) {
            MaintenanceScheduleDTO schedule = iterator.next();

            if (schedule.isCompleted()) {
                iterator.remove(); 
            }
        }
        all.sort(Comparator.comparing(MaintenanceScheduleDTO::getScheduleId));
        return all;
    }
    
    /**
     * get schedule by id
     * @param shcedule_id schedule id
     * @return schedule
     */
    public MaintenanceScheduleDTO getMaintScheduleByScheduleId(int shcedule_id){
        return scheduleDao.getById(shcedule_id);
    }

    /**
     * update schedule
     * @param schedule maintenance schedule
     * @return if update successfully
     */
    public boolean updateMaintSchedule(MaintenanceScheduleDTO schedule){
        return scheduleDao.update(schedule);
    }

    /**
     * delete schedule
     * @param schedule_id schedule id
     * @return id delete successfully
     */
    public boolean deleteScheduleById(int schedule_id){
        return scheduleDao.delete(schedule_id);
    }
    
    /**
     * get active schedule by alert
     * @param alert_id alert id
     * @return active schedule
     */
    public MaintenanceScheduleDTO getActiveScheduleByAlertId(int alert_id){
        MaintenanceScheduleDTO schedule =  scheduleDao.getByAlertId(alert_id);
        if(schedule != null){
            if(schedule.isCompleted())
                return null;
            else
                return schedule;
        }
        else
            return null;
    }

    /**
     * delete schedule
     * @param schedule_id schedule id
     * @return id delete successfully
     */
    public boolean deleteMaintSchedule(int schedule_id){
        return scheduleDao.delete(schedule_id);
    }
}
