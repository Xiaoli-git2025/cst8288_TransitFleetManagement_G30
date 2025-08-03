/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.alert_maint;
import model.MaintenanceScheduleDTO;
import dao.MaintenanceScheduleDAO;
/**
 * Create schedule command
 * @author Xiaoli He
 */

public class CreateMaintScheduleCommand implements Command {
    private MaintenanceScheduleDTO schedule;
    private MaintenanceScheduleDAO dao;

    /**
     * constructor
     * @param schedule schedule dto
     * @param dao schedule dao
     */
    public CreateMaintScheduleCommand(MaintenanceScheduleDTO schedule, MaintenanceScheduleDAO dao) {
        this.schedule = schedule;
        this.dao = dao;
    }

    /**
     * command execute
     * @return if the execute succeed
     */
    @Override
    public boolean execute() {
        return dao.add(schedule);
    }
}
