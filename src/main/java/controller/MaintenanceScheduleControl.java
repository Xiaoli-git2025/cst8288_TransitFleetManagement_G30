package controller;

import business.MaintenanceScheduleBusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MaintenanceScheduleDTO;

@WebServlet(name = "MaintenanceScheduleControl", urlPatterns = {"/MaintenanceScheduleControl"})
public class MaintenanceScheduleControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public MaintenanceScheduleBusinessLogic msblogic;

    @Override
    public void init() throws ServletException {
        msblogic = new MaintenanceScheduleBusinessLogic();
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
                /*
                case "AddStation":  
                    addStation(request, response);
                    break;
                case "UpdateStation": 
                    updateStation(request, response);
                    break;   
                */
                case "GetAllSchedule": 
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

    }
    

    public void getAllSchedules(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<MaintenanceScheduleDTO> allSchedules = msblogic.getAllObjects();
        if (allSchedules == null) {
            allSchedules = new ArrayList<>();
            Logger.getLogger(MaintenanceScheduleControl.class.getName())
                .log(Level.WARNING, "return null");
        }
        request.setAttribute("schedules", allSchedules);
        request.setAttribute("msg", request.getParameter("msg"));
        request.getRequestDispatcher("/views/operator/informationCheck/MaintenanceSchedule/MaintenanceScheduleView.jsp").forward(request, response);
    }
/*
    private void addStation(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        StationDTO station = new StationDTO();
        station.setStationId(Integer.parseInt(request.getParameter("station_id")));
        station.setStationName(request.getParameter("station_name"));
        boolean success = sblogic.addObject(station);
        if (success) {
            getAllStations(request,response);       
        } else {
            request.setAttribute("error", "Failed to add route");
            request.getRequestDispatcher("/views/admin/AddStation.jsp").forward(request, response);
        }
    }

    private void updateStation(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        StationDTO station = new StationDTO();
        station.setStationId(Integer.parseInt(request.getParameter("station_id")));
        station.setStationName(request.getParameter("station_name"));
        boolean success = sblogic.addObject(station);
        if (success) {
            getAllStations(request,response);       
        } else {
            request.setAttribute("error", "Failed to add route");
            request.getRequestDispatcher("/views/admin/UpdateStation.jsp").forward(request, response);
        }
    }

    private void deleteStation(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int stationId = Integer.parseInt(request.getParameter("stationId"));
        boolean success = sblogic.deleteObject(stationId);
        if (success) {
            getAllStations(request,response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
*/
    
}