/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.alert_maint;
import dao.MaintenanceAlertDAO;
import model.MaintenanceAlertDTO;
import model.MaintenanceScheduleDTO;
/**
 *
 * @author Administrator
 */
public class AlertObserver implements Observer {
    private MaintenanceAlertDAO m_alertDAO;

    public AlertObserver(MaintenanceAlertDAO m_alertDAO) {
        this.m_alertDAO = m_alertDAO;
    }
    
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

