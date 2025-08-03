package controller;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
import dao.VehicleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.VehicleDTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class VehicleControl
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
@WebServlet(name = "VehicleControl", urlPatterns = {"/VehicleControl"})
public class VehicleControl extends HttpServlet {
    /**
     * Data Access Object for vehicle operations.
     */
    private VehicleDAO vehicleDAO;
    /**
     * Initializes the servlet and creates a VehicleDAO instance.
     */
    @Override
    public void init() {
        vehicleDAO = new VehicleDAO();
    }
    /**
     * Handles POST requests and dispatches to the appropriate method
     * based on the "action" parameter.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made
     * @param response the HttpServletResponse object that contains the response the servlet returns
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                listVehicles(request, response);
                return;
            }

            switch (action) {
                case "ListVehicles":
                    listVehicles(request, response);
                    break;
                case "AddVehicle":
                    addVehicle(request, response);
                    break;
                case "UpdateVehicle":
                    updateVehicle(request, response);
                    break;
                case "AddVehicleForm":
                    showAddForm(request, response);
                    break;
                default:
                    listVehicles(request, response);
                    break;
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
     /**
     * Retrieves the list of all vehicles and forwards the request to the
     * vehicle list JSP page.
     * 
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a database error occurs
     * @throws IOException if an I/O error occurs
     */
    private void listVehicles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<VehicleDTO> vehicles = vehicleDAO.getAll();
            request.setAttribute("vehicles", vehicles);
            request.getRequestDispatcher("/views/admin/Vehicle.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }
    /**
     * Reads vehicle data from the request, creates a new VehicleDTO, and attempts to add it
     * to the database. Redirects to the vehicle list on success.
     * 
     * @param request the HttpServletRequest object containing form data
     * @param response the HttpServletResponse object
     * @throws ServletException if validation fails or adding the vehicle fails
     * @throws IOException if an I/O error occurs
     */
    private void addVehicle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VehicleDTO vehicle = new VehicleDTO();
            //vehicle.setVehicleId(getIntParameter(request,"vehicle_id"));
            vehicle.setVehicleNumber(getRequiredParameter(request, "vehicle_number"));
            BigDecimal consumptionRate = getConsumptionRateParameter(request);
            vehicle.setConsumptionRate(consumptionRate);
            vehicle.setMaxPassenger(getIntParameter(request, "max_passenger"));
            vehicle.setFuelType(getRequiredParameter(request, "fuel_type"));
            vehicle.setRouteId(getIntParameter(request, "route_id"));
            vehicle.setCapacity(getIntParameter(request, "capacity"));

            if (!vehicleDAO.add(vehicle)) {
                throw new ServletException("Failed to add vehicle");
            }
            response.sendRedirect("VehicleControl?action=ListVehicles");
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format: " + e.getMessage());
        }
    }
     /**
     * Reads vehicle data from the request, creates a VehicleDTO with the
     * specified ID, and attempts to update the existing vehicle in the database.
     * Redirects to the vehicle list on success.
     * 
     * @param request the HttpServletRequest object containing form data
     * @param response the HttpServletResponse object
     * @throws ServletException if validation fails or update fails
     * @throws IOException if an I/O error occurs
     */
    private void updateVehicle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int vehicleId = getIntParameter(request, "vehicle_id");
            VehicleDTO vehicle = new VehicleDTO();
            vehicle.setVehicleId(vehicleId);
            vehicle.setVehicleNumber(getRequiredParameter(request, "vehicle_number"));
            vehicle.setConsumptionRate(new BigDecimal(getRequiredParameter(request, "consumption_rate")));
            vehicle.setMaxPassenger(getIntParameter(request, "max_passenger"));
            vehicle.setFuelType(getRequiredParameter(request, "fuel_type"));
            vehicle.setRouteId(getIntParameter(request, "route_id"));
            vehicle.setCapacity(getIntParameter(request, "capacity"));
            if (!vehicleDAO.update(vehicle)) {
                throw new ServletException("Failed to update vehicle");
            }
            response.sendRedirect("VehicleControl?action=ListVehicles");
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format: " + e.getMessage());
        }
    }
    /**
     * Retrieves an existing vehicle by ID and forwards to the edit vehicle JSP page.
     * 
     * @param request the HttpServletRequest object containing the vehicle ID parameter
     * @param response the HttpServletResponse object
     * @throws ServletException if the vehicle ID is invalid or retrieval fails
     * @throws IOException if an I/O error occurs
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        String vehicleIdParam = request.getParameter("vehicleId");
        int vehicleId = Integer.parseInt(vehicleIdParam);
        VehicleDTO existingVehicle = vehicleDAO.getById(vehicleId);
        request.setAttribute("vehicle", existingVehicle);
        request.getRequestDispatcher("/views/admin/EditVehicle.jsp").forward(request, response); 
    } catch (NumberFormatException e) {
        throw new ServletException("Invalid vehicle ID format. Must be a number. Received: " 
            + request.getParameter("vehicleId"));
    }
}
    /**
     * Forwards the request to the add vehicle JSP page and action of deletion
     * 
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if forwarding fails
     * @throws IOException if an I/O error occurs
     */
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/AddVehicle.jsp").forward(request, response);
    }
     /**
     * Handles GET requests and dispatches to the appropriate method
     * based on the "action" parameter.
     * 
     * @param request  the HttpServletRequest object that contains the request the client made
     * @param response the HttpServletResponse object that contains the response the servlet returns
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                listVehicles(request, response);
                return;
            }

            switch (action) {
                case "deleteVehicle":
                    int vehicleId = getIntParameter(request, "vehicleId");
                    if (!vehicleDAO.delete(vehicleId)) {
                        throw new ServletException("Failed to delete vehicle");
                    }
                    response.sendRedirect("VehicleControl?action=ListVehicles");
                    break;
                case "EditVehicleForm":
                    showEditForm(request, response);
                    break;
                default:
                    listVehicles(request, response);
                    break;
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format: " + e.getMessage());
        }
    }

    /**
     * Retrieves a required request parameter as a trimmed String.
     * Throws ServletException if parameter is missing or empty.
     * 
     * @param request the HttpServletRequest object
     * @param paramName the name of the request parameter
     * @return the trimmed parameter value
     * @throws ServletException if parameter is missing or empty
     */
    private String getRequiredParameter(HttpServletRequest request, String paramName)
            throws ServletException {
        String value = request.getParameter(paramName);
        if (value == null || value.trim().isEmpty()) {
            throw new ServletException("Missing parameter: " + paramName);
        }
        return value.trim();
    }
    /**
     * Retrieves a required request parameter and parses it as an integer.
     * Throws ServletException if parameter is missing, empty or not a valid integer.
     * 
     * @param request the HttpServletRequest object
     * @param paramName the name of the request parameter
     * @return the integer value of the parameter
     * @throws ServletException if parameter is invalid
     */
    private int getIntParameter(HttpServletRequest request, String paramName)
            throws ServletException {
        String value = getRequiredParameter(request, paramName);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number for parameter: " + paramName);
        }
    }
    /**
     * Retrieves and parses the "consumption_rate" parameter as BigDecimal.
     * Throws ServletException if parameter is missing or invalid format.
     * 
     * @param request the HttpServletRequest object
     * @return BigDecimal representation of consumption rate
     * @throws ServletException if parameter is invalid
     */
    private BigDecimal getConsumptionRateParameter(HttpServletRequest request)
            throws ServletException {
        String value = getRequiredParameter(request, "consumption_rate");
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid consumption rate format");
        }
    }
}
