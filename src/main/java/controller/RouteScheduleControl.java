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

/**
 * RouteScheduleControl is a servlet that manages route schedule-related operations
 * such as adding, updating, deleting, and retrieving all route schedules.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
@WebServlet(name = "RouteScheduleControl", urlPatterns = {"/RouteScheduleControl"})
public class RouteScheduleControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public RouteScheduleBusinessLogic rsblogic;
    /**
     * Initializes the servlet and sets up the business logic object.
     *
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        rsblogic = new RouteScheduleBusinessLogic();
    }
    /**
     * Handles POST requests by delegating to appropriate methods based on the "action" parameter.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    /**
     * Handles GET requests by routing to the appropriate view or action.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            switch (action) {
                case "EditSchedule":
                    showEditForm(request, response);
                    break;
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
    
    /**
     * Retrieves all route schedules and forwards to the view.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
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
    /**
     * Adds a new route schedule from submitted form data.
     *
     * @param request  the HttpServletRequest containing form data
     * @param response the HttpServletResponse
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    private void addSchedule(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        RouteScheduleDTO schedule = new RouteScheduleDTO();
        schedule.setRouteId(Integer.parseInt(request.getParameter("route_id")));
        schedule.setStationId(Integer.parseInt(request.getParameter("station_id")));
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
    /**
     * Updates an existing route schedule based on form input.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    private void updateSchedule(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        RouteScheduleDTO schedule = new RouteScheduleDTO();
        schedule.setRouteId(Integer.parseInt(request.getParameter("route_id")));
        schedule.setStationId(Integer.parseInt(request.getParameter("station_id")));
        schedule.setScheduleId(Integer.parseInt(request.getParameter("schedule_id")));
        schedule.setScheduleNumber(Integer.parseInt(request.getParameter("schedule_number")));
        String arriveTime = request.getParameter("schedule_arrive_time");
        if(arriveTime != null && !arriveTime.isEmpty()) {
            if(arriveTime.length() == 5) {
                arriveTime += ":00";
            }
            schedule.setScheduleArriveTime(Time.valueOf(arriveTime));
        }
       
        String departTime = request.getParameter("schedule_depart_time");
        if(departTime != null && !departTime.isEmpty()) {
            if(departTime.length() == 5) {
                departTime += ":00";
            schedule.setScheduleDepartTime(Time.valueOf(departTime));
        }
        }
        boolean success = rsblogic.updateObject(schedule);
        if (success) {
            getAllSchedules(request,response);       
        } else {
            request.setAttribute("error", "Failed to add route");
            request.getRequestDispatcher("/views/admin/EditSchedule.jsp").forward(request, response);
        }
    }
    /**
     * Deletes a route schedule based on the given schedule ID.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
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
    /**
     * Shows the edit form for a specific route schedule identified by its ID.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException if the schedule ID is invalid or not found
     * @throws IOException if an I/O error occurs
     * @throws SQLException if a database access error occurs
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
            RouteScheduleDTO schedule = rsblogic.getObjById(scheduleId);

            if (schedule == null) {
                throw new ServletException("Schedule not found with ID: " + scheduleId);
            }

            request.setAttribute("schedule", schedule);
            request.getRequestDispatcher("/views/admin/EditSchedule.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid component ID format", e);
        }
    }
}