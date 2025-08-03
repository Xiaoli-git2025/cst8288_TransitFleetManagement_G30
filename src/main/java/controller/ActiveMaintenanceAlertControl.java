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
import business.alert_maint.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import dao.MaintenanceScheduleDAO;

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
    MaintenanceScheduleDAO scheduleDao;
    /**
     * init method
     */
    @Override
    public void init() {
        logic = new AlertBusinessLogic();
        scheduleDao = new MaintenanceScheduleDAO();
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
            int schedule_id =0;
            switch(get) {
                case "delete_alert":
                    alert_id = Integer.parseInt(request.getParameter("alert_id"));
                    if(logic.deleteMaintAlert(alert_id)){
                        List<MaintenanceAlertDTO> maintAlerts = logic.getActiveMaintAlert();
                        request.setAttribute("maint_alerts", maintAlerts);
                        request.getRequestDispatcher("/views/manager/ActiveAlertView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Delete Maintenance Alert Fail.");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                case "delete_schedule":
                    schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
                    if(logic.deleteScheduleById(schedule_id)){
                        List<MaintenanceScheduleDTO> schedules = logic.getActiveSchedules();
                        request.setAttribute("schedules", schedules);
                        request.getRequestDispatcher("/views/manager/MaintenanceScheduleView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Delete Maintenance Shcedule Fail.");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                case "add_sechedule":
                    alert_id = Integer.parseInt(request.getParameter("alert_id"));
                    MaintenanceScheduleDTO schedule = logic.getActiveScheduleByAlertId(alert_id);
                    if(schedule == null){
                        request.setAttribute("cur_alert", logic.getAlertByMaintAlertId(alert_id));
                        request.getRequestDispatcher("/views/manager/AddAlertScheduleView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("cur_schedule", schedule);
                        request.getRequestDispatcher("/views/manager/UpdateAlertScheduleView.jsp").forward(request, response);
                    }
                    break;
                case "update_schedule":
                    schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
                    MaintenanceScheduleDTO cur_schedule = logic.getMaintScheduleByScheduleId(schedule_id);
                    request.setAttribute("cur_schedule", cur_schedule);
                    request.getRequestDispatcher("/views/manager/UpdateAlertScheduleView.jsp").forward(request, response);
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
                case "Add Schedule":
                    java.sql.Date schedule_date = java.sql.Date.valueOf(request.getParameter("schedule_date"));
                    MaintenanceScheduleDTO schedule = new MaintenanceScheduleDTO();
                    schedule.setMaintenanceId(Integer.parseInt(request.getParameter("malert_id")));
                    schedule.setMaintenanceCost(new BigDecimal(request.getParameter("maint_cost")));
                    schedule.setNote(request.getParameter("note"));
                    schedule.setCompleted(false);
                    schedule.setScheduleDate(schedule_date);
                    //if(logic.addMaintSchedule(schedule))
                    Command createCmd = new CreateMaintScheduleCommand(schedule, scheduleDao);
                    if(createCmd.execute())
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
                    java.sql.Date scheduledate = java.sql.Date.valueOf(request.getParameter("schedule_date"));
                    MaintenanceScheduleDTO uschedule = new MaintenanceScheduleDTO();
                    uschedule.setScheduleId(Integer.parseInt(request.getParameter("schedule_id")));
                    uschedule.setMaintenanceId(Integer.parseInt(request.getParameter("malert_id")));
                    uschedule.setMaintenanceCost(new BigDecimal(request.getParameter("maint_cost")));
                    uschedule.setNote(request.getParameter("note"));
                    boolean isCompleted = request.getParameter("complete") != null;
                    uschedule.setCompleted(isCompleted);
                    uschedule.setScheduleDate(scheduledate);
                    //if(logic.updateMaintSchedule(uschedule))
                    MaintenanceSubject subject = (MaintenanceSubject) request.getSession().getAttribute("maintenanceSubject");
                    if (subject != null) {
                        subject.notifyObservers(uschedule);
                    }
                    Command updateCmd = new UpdateMaintScheduleCommand(uschedule, scheduleDao, subject);
                    if(updateCmd.execute())
                    {
                        List<MaintenanceScheduleDTO> schedules = logic.getActiveSchedules();
                        request.setAttribute("schedules", schedules);
                        request.getRequestDispatcher("/views/manager/MaintenanceScheduleView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Update Maintenance Shcedule Fail.");
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
