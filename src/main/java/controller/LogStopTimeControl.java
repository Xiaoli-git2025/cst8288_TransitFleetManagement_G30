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
import java.time.LocalDate;
import business.*;
import business.alert_maint.Command;
import business.alert_maint.CreateMaintScheduleCommand;
import business.alert_maint.MaintenanceSubject;
import business.alert_maint.UpdateMaintScheduleCommand;
import dao.MaintenanceScheduleDAO;
import java.math.BigDecimal;
import model.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrator
 */
@WebServlet(name = "LigStopTimeControl", urlPatterns = {"/LogStopTime"})
public class LogStopTimeControl extends HttpServlet {

    /**
     * business logic instance
     */
    private StopTimeBusinessLogic logic;
    /**
     * init method
     */
    @Override
    public void init() {
        logic = new StopTimeBusinessLogic();
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
            int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
            int user_id = (Integer)request.getSession().getAttribute("user_id");
            java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
            List<StationTimeDTO> logtime = logic.getLogTimeByScheduleId_Date_UserId(schedule_id, date, user_id);
            switch(get) {
                case "log_new":
                    if(logtime.size()==0){
                        RouteScheduleDTO schedule = logic.getScheduleById(schedule_id);
                        request.setAttribute("schedule", schedule);
                        request.getRequestDispatcher("/views/operator/LogStationTimeAddView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "You already log the time.");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                case "log_update":
                    if(logtime.size()>0){
                        RouteScheduleDTO schedule = logic.getScheduleById(schedule_id);
                        request.setAttribute("schedule", schedule);
                        request.setAttribute("logtime", logtime.get(0));
                        request.getRequestDispatcher("/views/operator/LogStationTimeUpdateView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "You haven't log the time.");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
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
            int user_id = (Integer)request.getSession().getAttribute("user_id");
            java.sql.Date log_date = java.sql.Date.valueOf(LocalDate.now());
            switch(action) {
                case "Load Route Schedule":
                    int station_id = Integer.parseInt(request.getParameter("station_id"));
                    //int schedule_number = Integer.parseInt(request.getParameter("schedule_number"));
                    int route_id = logic.getRouteByUserId(user_id).getRouteId();
                    List<RouteScheduleDTO> schedules = logic.getSchedulesByStationId_routeid(station_id, route_id);
                    request.setAttribute("route_schedules", schedules);
                    request.getRequestDispatcher("/views/operator/LogStationTime.jsp").forward(request, response);
                    break;
                case "Log Stop Time":                   
                    StationTimeDTO time = new StationTimeDTO();
                    time.setLogDate(log_date);
                    time.setScheduleId(Integer.parseInt(request.getParameter("schedule_id")));
                    time.setUserId(user_id);
                    time.setNote(request.getParameter("note"));
                    time.setArriveTime(java.sql.Time.valueOf(request.getParameter("ra_time") + ":00"));
                    time.setDepartTime(java.sql.Time.valueOf(request.getParameter("rd_time") + ":00"));
                    
                    if(logic.LogStopTime(time))
                    {
                        request.getRequestDispatcher("/views/operator/LogStationTime.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Log Stop Time Fail.");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                case "Update Stop Time":
                    java.sql.Date logdate = java.sql.Date.valueOf(LocalDate.now());
                    StationTimeDTO utime = new StationTimeDTO();
                    utime.setLogDate(logdate);
                    utime.setTimeId(Integer.parseInt(request.getParameter("time_id")));
                    utime.setScheduleId(Integer.parseInt(request.getParameter("schedule_id")));
                    utime.setUserId(user_id);
                    utime.setNote(request.getParameter("note"));
                    utime.setArriveTime(java.sql.Time.valueOf(request.getParameter("ra_time") + ":00"));
                    utime.setDepartTime(java.sql.Time.valueOf(request.getParameter("rd_time") + ":00"));
                    
                    if(logic.UpdateStopTime(utime))
                    {
                        request.getRequestDispatcher("/views/operator/LogStationTime.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Update Stop Time Fail.");
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
