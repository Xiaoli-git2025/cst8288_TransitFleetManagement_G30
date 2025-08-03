package controller;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
import business.RouteBusinessLogic;
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
import model.RouteDTO;
/**
 * RouteControl is a servlet that manages CRUD operations for routes in the system.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
@WebServlet(name = "RouteControl", urlPatterns = {"/RouteControl"})
public class RouteControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public RouteBusinessLogic rblogic;
    /**
     * Initializes the servlet by creating an instance of RouteBusinessLogic.
     *
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        rblogic = new RouteBusinessLogic();
    }
    /**
     * Handles POST requests for route operations based on the "action" parameter.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                getAllRoutes(request, response);
                return;
            }

            switch (action) {
                case "AddRoute":  
                    addRoute(request, response);
                    break;
                case "UpdateRoute": 
                    updateRoute(request, response);
                    break;              
                case "GetAllRoutes": 
                    getAllRoutes(request, response);
                    break;
                default:
                    getAllRoutes(request, response);  
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
     /**
     * Handles GET requests for route operations based on the "action" parameter.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            switch (action) {
                case "EditRoute":
                    showEditForm(request, response);
                    break;
                case "DeleteRoute":
                        deleteRoute(request, response);
                        break;
                default:
                    getAllRoutes(request,response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RouteControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Retrieves all routes and forwards them to the RouteView JSP.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws SQLException if a database error occurs
     * @throws ServletException if forwarding fails
     * @throws IOException if an I/O error occurs
     */
    public void getAllRoutes(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<RouteDTO> allRoutes = rblogic.getAllObjects();
        if (allRoutes == null) {
            allRoutes = new ArrayList<>();
            Logger.getLogger(RouteControl.class.getName())
                .log(Level.WARNING, "return null");
        }
        request.setAttribute("routes", allRoutes);
        request.setAttribute("msg", request.getParameter("msg"));
        request.getRequestDispatcher("/views/admin/RouteView.jsp").forward(request, response);
    }
    /**
     * Adds a new route using data from the request.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws SQLException if a database error occurs
     * @throws ServletException if adding fails or forwarding fails
     * @throws IOException if an I/O error occurs
     */
    private void addRoute(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        RouteDTO route = new RouteDTO();
        route.setRouteNumber(Integer.parseInt(request.getParameter("route_number")));
        route.setDescription(request.getParameter("description"));
        boolean success = rblogic.addObject(route);
        if (success) {
            getAllRoutes(request,response);       
        } else {
            request.setAttribute("error", "Failed to add route");
            request.getRequestDispatcher("/views/admin/AddRoute.jsp").forward(request, response);
        }
    }
    /**
     * Updates an existing route with data from the request.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws SQLException if a database error occurs
     * @throws ServletException if updating fails or forwarding fails
     * @throws IOException if an I/O error occurs
     */
    private void updateRoute(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        RouteDTO route = new RouteDTO();
        route.setRouteId(Integer.parseInt(request.getParameter("route_id")));
        route.setRouteNumber(Integer.parseInt(request.getParameter("route_number")));
        route.setDescription(request.getParameter("description"));
        boolean success = rblogic.updateObject(route);
        if (success) {
            getAllRoutes(request,response);
        } else {
            request.setAttribute("error", "Failed to update route");
            request.getRequestDispatcher("/views/admin/EditRoute.jsp").forward(request, response);
        }
    }
    /**
     * Deletes a route by ID.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws SQLException if a database error occurs
     * @throws ServletException if deletion fails
     * @throws IOException if an I/O error occurs
     */
    private void deleteRoute(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int routeId = Integer.parseInt(request.getParameter("routeId"));
        boolean success = rblogic.deleteObject(routeId);
        if (success) {
            getAllRoutes(request,response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    /**
     * Loads route data into the edit form.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException if the route is not found or format is invalid
     * @throws IOException if an I/O error occurs
     * @throws SQLException if a database error occurs
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            int routeId = Integer.parseInt(request.getParameter("routeId"));
            RouteDTO route = rblogic.getObjById(routeId);

            if (route == null) {
                throw new ServletException("Component not found with ID: " + routeId);
            }

            request.setAttribute("routes", route);
            request.getRequestDispatcher("/views/admin/EditRoute.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid component ID format", e);
        }
    }
}