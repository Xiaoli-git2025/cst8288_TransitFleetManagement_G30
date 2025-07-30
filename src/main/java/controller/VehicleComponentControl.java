package controller;

import business.VehicleComponentBusinessLogic;
import model.VehicleComponentDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/VehicleComponentControl")
public class VehicleComponentControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public VehicleComponentBusinessLogic componentLogic;

    @Override
    public void init() throws ServletException {
        componentLogic = new VehicleComponentBusinessLogic();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("authenticated") == null) {
                response.sendRedirect(request.getContextPath() + "/views/admin/VehicleComponent.jsp");
                return;
            }

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
                case "DeleteComponent": 
                    deleteComponent(request, response);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void getAllComponents(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<VehicleComponentDTO> allComponents = componentLogic.getAllObjects();
        request.setAttribute("vehicleComponents", allComponents);
        request.setAttribute("msg", request.getParameter("msg"));
        request.getRequestDispatcher("/views/admin/VehicleComponent.jsp").forward(request, response);
    }

    private void addComponent(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        VehicleComponentDTO component = new VehicleComponentDTO();
        component.setComponentId(Integer.parseInt(request.getParameter("Component_id")));
        component.setComponentName(request.getParameter("Component_name"));
        component.setVehicleId(Integer.parseInt(request.getParameter("Vehicle_id")));
        component.setUsedHour(Integer.parseInt(request.getParameter("used_hour")));
        component.setThresholdHour(Integer.parseInt(request.getParameter("threshold_hour")));

        boolean success = componentLogic.addObject(component);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/VehicleComponentControl?action=GetAllComponents&msg=Component+added+successfully");
        } else {
            request.setAttribute("error", "Failed to add component");
            request.getRequestDispatcher("/views/admin/AddComponent.jsp").forward(request, response);
        }
    }

    private void updateComponent(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        VehicleComponentDTO component = new VehicleComponentDTO();
        component.setComponentId(Integer.parseInt(request.getParameter("Component_id")));
        component.setComponentName(request.getParameter("Component_name"));
        component.setVehicleId(Integer.parseInt(request.getParameter("Vehicle_id")));
        component.setUsedHour(Integer.parseInt(request.getParameter("used_hour")));
        component.setThresholdHour(Integer.parseInt(request.getParameter("threshold_hour")));

        boolean success = componentLogic.updateObject(component);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/VehicleComponentControl?action=GetAllComponents&msg=Component+updated+successfully");
        } else {
            request.setAttribute("error", "Failed to update component");
            request.getRequestDispatcher("/views/admin/EditComponent.jsp").forward(request, response);
        }
    }

    private void deleteComponent(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int componentId = Integer.parseInt(request.getParameter("componentId"));
        boolean success = componentLogic.deleteObject(componentId);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/VehicleComponentControl?action=GetAllComponents&msg=Component+deleted+successfully");
        } else {
            request.setAttribute("error", "Failed to delete component");
            getAllComponents(request, response); 
        }
    }
}