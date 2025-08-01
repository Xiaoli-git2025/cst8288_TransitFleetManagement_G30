package controller;

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

@WebServlet(name = "VehicleControl", urlPatterns = {"/VehicleControl"})
public class VehicleControl extends HttpServlet {

    private VehicleDAO vehicleDAO;

    @Override
    public void init() {
        vehicleDAO = new VehicleDAO();
    }

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
                case "EditVehicleForm":
                    showEditForm(request, response);
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

    private void addVehicle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VehicleDTO vehicle = new VehicleDTO();
            vehicle.setVehicleId(getIntParameter(request,"vehicle_id"));
            vehicle.setVehicleNumber(getRequiredParameter(request, "vehicle_number"));
            BigDecimal consumptionRate = getConsumptionRateParameter(request);
            vehicle.setConsumptionRate(consumptionRate);
            vehicle.setMaxPassenger(getIntParameter(request, "max_passenger"));
            vehicle.setFuelType(getRequiredParameter(request, "fuel_type"));
            vehicle.setRouteId(getIntParameter(request, "route_id"));
            vehicle.setCapacity(getIntParameter(request, "capacity"));
            vehicle.setComponentId(getIntParameter(request, "component_id"));

            if (!vehicleDAO.add(vehicle)) {
                throw new ServletException("Failed to add vehicle");
            }
            response.sendRedirect("VehicleControl?action=ListVehicles");
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format: " + e.getMessage());
        }
    }

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
            vehicle.setComponentId(getIntParameter(request, "component_id"));

            if (!vehicleDAO.update(vehicle)) {
                throw new ServletException("Failed to update vehicle");
            }
            response.sendRedirect("VehicleControl?action=ListVehicles");
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format: " + e.getMessage());
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int vehicleId = getIntParameter(request, "vehicleId");
            VehicleDTO existingVehicle = vehicleDAO.getById(vehicleId);
            if (existingVehicle == null) {
                throw new ServletException("Vehicle not found");
            }
            request.setAttribute("vehicle", existingVehicle);
            request.getRequestDispatcher("/views/admin/EditVehicle.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid vehicle ID");
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/AddVehicle.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                listVehicles(request, response);
                return;
            }

            if (action.equals("deleteVehicle")) {
                int vehicleId = getIntParameter(request, "vehicleId");
                if (!vehicleDAO.delete(vehicleId)) {
                    throw new ServletException("Failed to delete vehicle");
                }
                response.sendRedirect("VehicleControl?action=ListVehicles");
            } else {
                listVehicles(request, response);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format: " + e.getMessage());
        }
    }

    // Helper methods for parameter handling
    private String getRequiredParameter(HttpServletRequest request, String paramName)
            throws ServletException {
        String value = request.getParameter(paramName);
        if (value == null || value.trim().isEmpty()) {
            throw new ServletException("Missing parameter: " + paramName);
        }
        return value.trim();
    }

    private int getIntParameter(HttpServletRequest request, String paramName)
            throws ServletException {
        String value = getRequiredParameter(request, paramName);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number for parameter: " + paramName);
        }
    }

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