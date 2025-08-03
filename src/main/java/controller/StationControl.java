package controller;

import business.StationBusinessLogic;
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
import model.StationDTO;
import model.VehicleComponentDTO;

@WebServlet(name = "StationControl", urlPatterns = {"/StationControl"})
public class StationControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public StationBusinessLogic sblogic;

    @Override
    public void init() throws ServletException {
        sblogic = new StationBusinessLogic();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                getAllStations(request, response);
                return;
            }

            switch (action) {
                case "AddStation":  
                    addStation(request, response);
                    break;
                case "UpdateStation": 
                    updateStation(request, response);
                    break;              
                case "GetAllStation": 
                    getAllStations(request, response);
                    break;
                default:
                    getAllStations(request, response);  
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
                case "EditStation":
                    showEditForm(request, response);
                    break;
                case "DeleteStation":
                        deleteStation(request, response);
                        break;
                default:
                    getAllStations(request,response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StationControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void getAllStations(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<StationDTO> allStations = sblogic.getAllObjects();
        if (allStations == null) {
            allStations = new ArrayList<>();
            Logger.getLogger(RouteControl.class.getName())
                .log(Level.WARNING, "return null");
        }
        request.setAttribute("stations", allStations);
        request.setAttribute("msg", request.getParameter("msg"));
        request.getRequestDispatcher("/views/admin/StationView.jsp").forward(request, response);
    }

    private void addStation(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        StationDTO station = new StationDTO();
        //station.setStationId(Integer.parseInt(request.getParameter("station_id")));
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
        boolean success = sblogic.updateObject(station);
        if (success) {
            getAllStations(request,response);       
        } else {
            request.setAttribute("error", "Failed to edit route");
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
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            int stationId = Integer.parseInt(request.getParameter("stationId"));
            StationDTO station = sblogic.getObjById(stationId);

            if (station == null) {
                throw new ServletException("Component not found with ID: " + stationId);
            }

            request.setAttribute("stations", station);
            request.getRequestDispatcher("/views/admin/EditStation.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid component ID format", e);
        }
    }

}