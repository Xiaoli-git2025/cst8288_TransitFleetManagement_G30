/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.alert_maint;
import model.MaintenanceScheduleDTO;
/**
 *
 * @author Xiaoli He
 */
public interface Observer {
    void update(MaintenanceScheduleDTO schedule);
}

