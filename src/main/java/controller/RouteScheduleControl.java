package controller;

import business.RouteScheduleBusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RouteScheduleDTO;

@WebServlet(name = "RouteScheduleControl", urlPatterns = {"/RouteScheduleControl"})
public class RouteScheduleControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public RouteScheduleBusinessLogic rsblogic;
    @Override
    public void init() throws ServletException {
        rsblogic = new RouteScheduleBusinessLogic();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                getAllSchedules(request, response);
                return;
            }

            switch (action) {
                case "AddRouteSchedule":  
                    addSchedule(request, response);
                    break;
                case "UpdateRouteSchedule": 
                    updateSchedule(request, response);
                    break;              
                case "GetAllSchedules": 
                    getAllSchedules(request, response);
                    break;
                default:
                    getAllSchedules(request, response);  
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            switch (action) {
                case "DeleteRouteSchedule":
                        deleteSchedule(request, response);
                        break;
                default:
                    getAllSchedules(request,response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RouteScheduleControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void getAllSchedules(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<RouteScheduleDTO> allSchedules = rsblogic.getAllObjects();
        if (allSchedules == null) {
            allSchedules = new ArrayList<>();
            Logger.getLogger(RouteScheduleControl.class.getName())
                .log(Level.WARNING, "return null");
        }
        request.setAttribute("schedules", allSchedules);
        request.setAttribute("msg", request.getParameter("msg"));
        request.getRequestDispatcher("/views/admin/RouteScheduleView.jsp").forward(request, response);
    }

    private void addSchedule(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        RouteScheduleDTO schedule = new RouteScheduleDTO();
        schedule.setRouteId(Integer.parseInt(request.getParameter("route_id")));
        schedule.setStationId(Integer.parseInt(request.getParameter("station_id")));
        schedule.setScheduleId(Integer.parseInt(request.getParameter("schedule_id")));
        schedule.setScheduleNumber(Integer.parseInt(request.getParameter("schedule_number")));
        schedule.setScheduleArriveTime(Time.valueOf(request.getParameter("schedule_arrive_time")));  
        schedule.setScheduleDepartTime(Time.valueOf(request.getParameter("schedule_depart_time")));

        boolean success = rsblogic.addObject(schedule);
        if (success) {
            getAllSchedules(request,response);       
        } else {
            request.setAttribute("error", "Failed to add route");
            request.getRequestDispatcher("/views/admin/AddSchedule.jsp").forward(request, response);
        }
    }

    private void updateSchedule(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        RouteScheduleDTO schedule = new RouteScheduleDTO();
        schedule.setRouteId(Integer.parseInt(request.getParameter("route_id")));
        schedule.setStationId(Integer.parseInt(request.getParameter("station_id")));
        schedule.setScheduleId(Integer.parseInt(request.getParameter("schedule_id")));
        schedule.setScheduleNumber(Integer.parseInt(request.getParameter("schedule_number")));
        schedule.setScheduleArriveTime(Time.valueOf(request.getParameter("schedule_arrive_time")));  
        schedule.setScheduleDepartTime(Time.valueOf(request.getParameter("schedule_depart_time")));

        boolean success = rsblogic.addObject(schedule);
        if (success) {
            getAllSchedules(request,response);       
        } else {
            request.setAttribute("error", "Failed to add route");
            request.getRequestDispatcher("/views/admin/UpdateSchedule.jsp").forward(request, response);
        }
    }

    private void deleteSchedule(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        boolean success = rsblogic.deleteObject(scheduleId);
        if (success) {
            getAllSchedules(request,response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}