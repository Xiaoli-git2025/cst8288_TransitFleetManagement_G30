/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import business.*;
import model.*;
import java.util.List;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "ManagerControl", urlPatterns = {"/Manager"})
public class ManagerControl extends HttpServlet {

    /**
     * business logic instance
     */
    private AlertBusinessLogic logic;
    /**
     * init method
     */
    @Override
    public void init() {
        logic = new AlertBusinessLogic();
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String get = request.getParameter("get");
            switch(get) {
                case "alert_type":
                    //getAlertTypes, list, add, delete, update
                    request.getRequestDispatcher("/views/manager/AlertTypeView.jsp").forward(request, response);
                    break;
                case "all_alerts":
                    //getActiveAlerts, list, update resolved
                    List<MaintenanceAlertDTO> malerts = logic.getActiveMaintAlert();
                    request.setAttribute("maint_alerts", malerts);
                    request.getRequestDispatcher("/views/manager/ActiveAlertView.jsp").forward(request, response);
                    break;
                case "maintenance_schedule":
                    //getActiveMaintenanceSchedule, list, add, delete, update
                    List<MaintenanceScheduleDTO> schedules = logic.getActiveSchedules();
                    request.setAttribute("schedules", schedules);
                    request.getRequestDispatcher("/views/manager/MaintenanceScheduleView.jsp").forward(request, response);
                    break;
                case "operator_performance":
                    //getPerformance, list, sort, filtereByOperatorName
                    request.getRequestDispatcher("/views/manager/OperatorPerformanceView.jsp").forward(request, response);
                    break;
                case "maintenance_report":
                    //getAllMaintenanceSchedule, list, sort
                    request.getRequestDispatcher("/views/manager/AllMaintScheduleView.jsp").forward(request, response);
                    break;
                case "maintenance_cost":
                    //getAllCostOnMaintenance, list, group by alert type, vehicle, component
                    request.getRequestDispatcher("/views/manager/AllCostOnMaintView.jsp").forward(request, response);
                    break;
                case "fuel_energy_cost":
                    //getAllCostOnFuelEnergy, list, group by vehicle type, vehicle
                    request.getRequestDispatcher("/views/manager/AllCostOnFuelEnergyView.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
