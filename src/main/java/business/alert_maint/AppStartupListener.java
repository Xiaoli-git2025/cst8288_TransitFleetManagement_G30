package business.alert_maint;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.annotation.WebListener;
import dao.MaintenanceAlertDAO;


public class AppStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MaintenanceAlertDAO alertDAO = new MaintenanceAlertDAO();
        AlertObserver alertObserver = new AlertObserver(alertDAO);

        MaintenanceSubject subject = new MaintenanceSubject();
        subject.addObserver(alertObserver);

        sce.getServletContext().setAttribute("maintenanceSubject", subject);
    }
}