/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.alert_maint;
import model.MaintenanceScheduleDTO;
/**
 * observer interface
 * @author Xiaoli He
 */
public interface Observer {
    /**
     * observer update method
     * @param schedule schedule object
     */
    void update(MaintenanceScheduleDTO schedule);
}

