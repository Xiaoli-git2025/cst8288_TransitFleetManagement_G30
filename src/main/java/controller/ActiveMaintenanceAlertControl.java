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
import business.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "ActiveMaintenanceAlertControl", urlPatterns = {"/ActiveMaintenanceAlert"})
public class ActiveMaintenanceAlertControl extends HttpServlet {

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
            int alert_id = 0;
            switch(get) {
                case "delete_alert":
                    alert_id = Integer.parseInt(request.getParameter("alert_id"));
                    //vehicle_id = logic.getVehicleIdByAlertId(alert_id);
                    logic.deleteMaintAlert(alert_id);
                    List<MaintenanceAlertDTO> maintAlerts = logic.getActiveMaintAlert();
                    request.setAttribute("maint_alerts", maintAlerts);
                    request.getRequestDispatcher("/views/manager/ActiveAlertView.jsp").forward(request, response);
                    break;
                case "add_sechedule":
                    alert_id = Integer.parseInt(request.getParameter("alert_id"));
                    request.setAttribute("cur_alert", logic.getAlertByMaintAlertId(alert_id));
                    request.getRequestDispatcher("/views/manager/AddAlertScheduleView.jsp").forward(request, response);
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
        try{
            String action = request.getParameter("action");
            switch(action) {
                case "Add Shecdule":
                    java.sql.Date schedule_date = java.sql.Date.valueOf(request.getParameter("schedule_date"));
                    MaintenanceScheduleDTO schedule = new MaintenanceScheduleDTO();
                    schedule.setMaintenanceId(Integer.parseInt(request.getParameter("malert_id")));
                    schedule.setMaintenanceCost(new BigDecimal(request.getParameter("maint_cost")));
                    schedule.setNote(request.getParameter("note"));
                    schedule.setCompleted(false);
                    schedule.setScheduleDate(schedule_date);
                    if(logic.addMaintSchedule(schedule))
                    {
                        List<MaintenanceScheduleDTO> schedules = logic.getActiveSchedules();
                        request.setAttribute("schedules", schedules);
                        request.getRequestDispatcher("/views/manager/MaintenanceScheduleView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Add Maintenance Shcedule Fail.");
                            request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                case "Update Schedule":
                    //vehicle_id = (Integer)request.getSession().getAttribute("vehicle_id");
                    int malertid = Integer.parseInt(request.getParameter("malert_id"));
                    int alertid = Integer.parseInt(request.getParameter("alert_type"));
                    int componentid = Integer.parseInt(request.getParameter("component_id"));
                    String date = request.getParameter("alert_date");
                    java.sql.Date alertdate = java.sql.Date.valueOf(date);
                    MaintenanceAlertDTO maintalert = new MaintenanceAlertDTO();
                    maintalert.setMaintenanceId(malertid);
                    maintalert.setAlertId(alertid);
                    maintalert.setComponentId(componentid);
                    maintalert.setAlertDate(alertdate);
                    maintalert.setResolved(false);
                    if(logic.updateMaintAlert(maintalert))
                    {
                        List<MaintenanceScheduleDTO> schedules = logic.getActiveSchedules();
                        request.setAttribute("schedules", schedules);
                        request.getRequestDispatcher("/views/manager/MaintenanceScheduleView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Update Maintenance Schedule Fail.");
                            request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                default:
                    break;
            } 
        }catch (Exception ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
