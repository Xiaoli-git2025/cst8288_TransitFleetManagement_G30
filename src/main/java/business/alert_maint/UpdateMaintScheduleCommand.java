/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.alert_maint;
import model.MaintenanceScheduleDTO;
import dao.MaintenanceScheduleDAO;
/**
 * update schedule command
 * @author Xiaoli He
 */
public class UpdateMaintScheduleCommand implements Command {
    private MaintenanceScheduleDTO schedule;
    private MaintenanceScheduleDAO dao;
    private MaintenanceSubject subject;

    /**
     * constructor
     * @param schedule schedule object
     * @param dao schedule dao
     * @param subject maintenance subject
     */
    public UpdateMaintScheduleCommand(MaintenanceScheduleDTO schedule, MaintenanceScheduleDAO dao, MaintenanceSubject subject) {
        this.schedule = schedule;
        this.dao = dao;
        this.subject = subject;
    }

    /**
     * command execute 
     * @return if the command execute succeed
     */
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
