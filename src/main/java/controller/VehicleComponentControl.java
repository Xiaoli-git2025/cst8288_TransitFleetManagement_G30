package controller;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
import business.VehicleComponentBusinessLogic;
import model.VehicleComponentDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * VehicleComponentControl is a servlet responsible for handling requests related to 
 * vehicle components.
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
@WebServlet(name = "VehicleComponentControl", urlPatterns = {"/VehicleComponentControl"})
public class VehicleComponentControl extends HttpServlet {
    /** Business logic object for component-related operations */
    private static final long serialVersionUID = 1L;
    public VehicleComponentBusinessLogic componentLogic;
     /**
     * Initializes the servlet and instantiates the business logic layer.
     * 
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        componentLogic = new VehicleComponentBusinessLogic();
    }
    /**
     * Handles HTTP POST requests and dispatches based on the "action" parameter.
     * 
     * @param request  the HTTP request object
     * @param response the HTTP response object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                getAllComponents(request, response);
                return;
            }
            switch (action) {
                case "AddComponent":
                    addComponent(request, response);
                    break;
                case "UpdateComponent":
                    updateComponent(request, response);
                    break;
                case "GetAllComponents":
                    getAllComponents(request, response);
                    break;
                default:
                    getAllComponents(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    /**
     * Handles HTTP GET requests and dispatches based on the "actions" parameter.
     * 
     * @param request  the HTTP request object
     * @param response the HTTP response object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String actions = request.getParameter("actions");

            switch (actions) {

                case "EditComponent":
                    showEditForm(request, response);
                    break;
                case "DeleteComponent":
                    deleteComponent(request, response);
                    break;
                default:
                    getAllComponents(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehicleComponentControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Retrieves all vehicle components from the database and forwards them to the view.
     * 
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    public void getAllComponents(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<VehicleComponentDTO> allComponents = componentLogic.getAllObjects();
        request.setAttribute("vehicleComponents", allComponents);
        request.setAttribute("msg", request.getParameter("msg"));
        request.getRequestDispatcher("/views/admin/VehicleComponent.jsp").forward(request, response);
    }
    /**
     * Adds a new vehicle component using form data from the request.
     * 
     * @param request  the HTTP request containing component data
     * @param response the HTTP response
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    private void addComponent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        VehicleComponentDTO component = new VehicleComponentDTO();
        //component.setComponentId(Integer.parseInt(request.getParameter("component_id")));
        component.setComponentName(request.getParameter("component_name"));
        component.setVehicleId(Integer.parseInt(request.getParameter("vehicle_id")));
        component.setUsedHour(Integer.parseInt(request.getParameter("used_hour")));
        component.setThresholdHour(Integer.parseInt(request.getParameter("threshold_hour")));

        boolean success = componentLogic.addObject(component);
        if (success) {
            getAllComponents(request, response);
        } else {
            request.setAttribute("error", "Failed to add component");
            request.getRequestDispatcher("/views/admin/AddComponent.jsp").forward(request, response);
        }
    }
    /**
     * Displays the edit form for a specific vehicle component by ID.
     * 
     * @param request  the HTTP request containing component ID
     * @param response the HTTP response
     * @throws ServletException if the ID is invalid or component not found
     * @throws IOException if an I/O error occurs
     * @throws SQLException if a database access error occurs
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            int componentId = Integer.parseInt(request.getParameter("componentId"));
            VehicleComponentDTO component = componentLogic.getObjById(componentId);

            if (component == null) {
                throw new ServletException("Component not found with ID: " + componentId);
            }

            request.setAttribute("vehicleComponents", component);
            request.getRequestDispatcher("/views/admin/EditComponent.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid component ID format", e);
        }
    }
    /**
     * Updates an existing vehicle component using form data from the request.
     * 
     * @param request  the HTTP request containing updated component data
     * @param response the HTTP response
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    private void updateComponent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        VehicleComponentDTO component = new VehicleComponentDTO();
        component.setComponentId(Integer.parseInt(request.getParameter("component_id")));
        component.setComponentName(request.getParameter("component_name"));
        component.setVehicleId(Integer.parseInt(request.getParameter("vehicle_id")));
        component.setUsedHour(Integer.parseInt(request.getParameter("used_hour")));
        component.setThresholdHour(Integer.parseInt(request.getParameter("threshold_hour")));

        boolean success = componentLogic.updateObject(component);
        if (success) {
            getAllComponents(request, response);
        } else {
            request.setAttribute("error", "Failed to update component");
            request.getRequestDispatcher("/views/admin/EditComponent.jsp").forward(request, response);
        }
    }
    /**
     * Deletes a vehicle component based on its ID.
     * 
     * @param request  the HTTP request containing the component ID
     * @param response the HTTP response
     * @throws SQLException if a database access error occurs
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    private void deleteComponent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int componentId = Integer.parseInt(request.getParameter("componentId"));
        boolean success = componentLogic.deleteObject(componentId);
        if (success) {
            getAllComponents(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
 
}
