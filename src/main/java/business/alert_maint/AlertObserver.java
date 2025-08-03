/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.alert_maint;
import dao.MaintenanceAlertDAO;
import model.MaintenanceAlertDTO;
import model.MaintenanceScheduleDTO;
/**
 * Observer added to maintenance alert resolved based on the maintenance schedule completed
 * @author Xiaoli He
 */
public class AlertObserver implements Observer {
    private MaintenanceAlertDAO m_alertDAO;

    /**
     * constructor
     * @param m_alertDAO dao for maintenance alert
     */
    public AlertObserver(MaintenanceAlertDAO m_alertDAO) {
        this.m_alertDAO = m_alertDAO;
    }
    
    /**
     * observer update resolved based on maintenance schedule
     * @param schedule 
     */
    public void update(MaintenanceScheduleDTO schedule) {
        if (schedule.isCompleted()) {
            // Find the related alert using the schedule’s maintenance_id
            MaintenanceAlertDTO alert = m_alertDAO.getById(schedule.getMaintenanceId());
            if (alert != null) {
                alert.setResolved(true);
                m_alertDAO.update(alert);
            }
        }
    }
}

