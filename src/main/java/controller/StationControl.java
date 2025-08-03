package controller;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
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

/**
 * StationControl is a servlet that manages CRUD operations for transit stations.
 * It supports adding, updating, deleting, and listing stations, as well as showing 
 * the edit form for a specific station.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
@WebServlet(name = "StationControl", urlPatterns = {"/StationControl"})
public class StationControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
    public StationBusinessLogic sblogic;
    /**
     * Initializes the servlet by instantiating the StationBusinessLogic.
     * 
     * @throws ServletException if initialization fails
     */
 
    @Override
    public void init() throws ServletException {
        sblogic = new StationBusinessLogic();
    }
    /**
     * Handles HTTP POST requests by delegating to the appropriate method based on the "action" parameter.
     * 
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
     /**
     * Handles HTTP GET requests by dispatching actions like edit, delete, or listing all stations.
     * 
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    
    /**
     * Retrieves all stations and forwards the request to the station list view.
     * 
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
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
    /**
     * Adds a new station using form data from the request.
     * 
     * @param request  the HTTP request containing station data
     * @param response the HTTP response
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
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
    /**
     * Updates an existing station with the submitted form data.
     * 
     * @param request  the HTTP request containing updated station data
     * @param response the HTTP response
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
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
    /**
     * Deletes a station based on the provided station ID.
     * 
     * @param request  the HTTP request containing the station ID
     * @param response the HTTP response
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
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
    /**
     * Retrieves a station by ID and forwards the request to the edit form page.
     * 
     * @param request  the HTTP request containing the station ID
     * @param response the HTTP response
     * @throws ServletException if the ID is invalid or station is not found
     * @throws IOException if an I/O error occurs
     * @throws SQLException if a database access error occurs
     */
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