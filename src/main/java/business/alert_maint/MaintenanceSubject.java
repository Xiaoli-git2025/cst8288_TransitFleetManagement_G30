/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.alert_maint;
import java.util.ArrayList;
import java.util.List;
import model.MaintenanceScheduleDTO;
/**
 * Subject for maintenance schedule
 * @author Xiaoli He
 */
public class MaintenanceSubject {
    private List<Observer> observers = new ArrayList<>();

    /**
     * add observer
     * @param o observer object
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * notify observer
     * @param schedule schedule object
     */
    public void notifyObservers(MaintenanceScheduleDTO schedule) {
        for (Observer o : observers) {
            o.update(schedule);
        }
    }

}
