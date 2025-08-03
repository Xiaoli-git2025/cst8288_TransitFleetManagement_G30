/*
 * Controller servlet for admin operations in the Transit Fleet Management system.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import business.*;
import business.builder.VehicleBusinessLogic;
import model.*;

/**
 * AdminControl servlet handles administrative tasks such as viewing and managing
 * routes, stations, vehicles, components, and route schedules.
 * This servlet routes GET requests to appropriate business logic and JSP views.
 * 
 * URL mapping: /Admin
 * 
 * Example usage:
 *   /Admin?get=route
 *   /Admin?get=vehicle
 * 
 * This servlet uses business logic classes to fetch data and forwards it
 * to the appropriate JSP view.
 * 
 * @author Xiaoli He
 */
@WebServlet(name = "AdminControl", urlPatterns = {"/Admin"})
public class AdminControl extends HttpServlet {

    /** Business logic for managing routes. */
    private RouteBusinessLogic logic;

    /** Business logic for managing vehicle components. */
    private VehicleComponentBusinessLogic vclogic;

    /** Business logic for managing vehicles. */
    private VehicleBusinessLogic vlogic;

    /** Business logic for managing stations. */
    private StationBusinessLogic sblogic;

    /** Business logic for managing route schedules. */
    private RouteScheduleBusinessLogic rsblogic;

    /**
     * Initializes business logic instances when servlet is first loaded.
     */
    @Override
    public void init() {
        logic = new RouteBusinessLogic();
        vclogic = new VehicleComponentBusinessLogic();
        vlogic = new VehicleBusinessLogic();
        sblogic = new StationBusinessLogic();
        rsblogic = new RouteScheduleBusinessLogic();
    }

    /**
     * Handles HTTP GET requests and routes them based on 'get' query parameter.
     * Supports:
     * - get=route → shows all routes
     * - get=station → shows all stations
     * - get=route_schedule → shows all route schedules
     * - get=vehicle → shows all vehicles
     * - get=component → shows all vehicle components
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String get = request.getParameter("get");
            switch (get) {
                case "route":
                    List<RouteDTO> routes = logic.getAllObjects();
                    request.setAttribute("routes", routes);
                    request.getRequestDispatcher("/views/admin/RouteView.jsp").forward(request, response);
                    break;
                case "station":
                    List<StationDTO> stations = sblogic.getAllObjects();
                    request.setAttribute("stations", stations);
                    request.getRequestDispatcher("/views/admin/StationView.jsp").forward(request, response);
                    break;
                case "route_schedule":
                    List<RouteScheduleDTO> routeSchedules = rsblogic.getAllObjects();
                    request.setAttribute("schedules", routeSchedules);
                    request.getRequestDispatcher("/views/admin/RouteScheduleView.jsp").forward(request, response);
                    break;
                case "vehicle":
                    List<VehicleDTO> vehicles = vlogic.getAllVehicles();
                    request.setAttribute("vehicles", vehicles);
                    request.getRequestDispatcher("/views/admin/Vehicle.jsp").forward(request, response);
                    break;
                case "component":
                    List<VehicleComponentDTO> vehicleComponents = vclogic.getAllObjects();
                    request.setAttribute("vehicleComponents", vehicleComponents);
                    request.getRequestDispatcher("/views/admin/VehicleComponent.jsp").forward(request, response);
                    break;
                default:
                    // If no match found, you could redirect to a default admin page or show error
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter: " + get);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + ex.getMessage());
        }
    }

    /**
     * Handles HTTP POST requests. Currently not implemented.
     * 
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Not implemented
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a string containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "AdminControl servlet handles administrative views and actions.";
    }
}
