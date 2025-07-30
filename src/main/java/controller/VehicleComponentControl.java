package controller;

import business.VehicleComponentBusinessLogic;
import static cn.jdevelops.log.LogUtils.error;
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

@WebServlet(name = "VehicleComponentControl", urlPatterns = {"/VehicleComponentControl"})
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String actions = request.getParameter("actions");
            
            switch (actions) {
                case "DeleteComponent":
                        deleteComponent(request, response);
                        break;
                default:
                    getAllComponents(request,response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehicleComponentControl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        //component.setComponentId(Integer.parseInt(request.getParameter("Component_id")));
        component.setComponentName(request.getParameter("Component_name"));
        component.setVehicleId(Integer.parseInt(request.getParameter("Vehicle_id")));
        component.setUsedHour(Integer.parseInt(request.getParameter("used_hour")));
        component.setThresholdHour(Integer.parseInt(request.getParameter("threshold_hour")));

        boolean success = componentLogic.addObject(component);
        if (success) {
            getAllComponents(request,response);       
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
            getAllComponents(request,response);
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
            getAllComponents(request,response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}