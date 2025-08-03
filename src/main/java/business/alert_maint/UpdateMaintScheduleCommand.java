/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.alert_maint;
import model.MaintenanceScheduleDTO;
import dao.MaintenanceScheduleDAO;
/**
 *
 * @author Xiaoli He
 */
public class UpdateMaintScheduleCommand implements Command {
    private MaintenanceScheduleDTO schedule;
    private MaintenanceScheduleDAO dao;
    private MaintenanceSubject subject;

    public UpdateMaintScheduleCommand(MaintenanceScheduleDTO schedule, MaintenanceScheduleDAO dao, MaintenanceSubject subject) {
        this.schedule = schedule;
        this.dao = dao;
        this.subject = subject;
    }

    @Override
    public boolean execute() {
        if(dao.update(schedule)){
            subject.notifyObservers(schedule);
            return true;
        }
        else
            return false;
    }
}
