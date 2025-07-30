/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import business.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "MaintenanceAlertControl", urlPatterns = {"/MaintenanceAlert"})
public class MaintenanceAlertControl extends HttpServlet {

    /**
     * business logic instance
     */
    private AlertBusinessLogic logic;
    /**
     * init method
     */
    @Override
    public void init() {
        logic = new AlertBusinessLogic();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String get = request.getParameter("get");
            int user_id = (Integer)request.getSession().getAttribute("user_id");
            int alert_id = 0;
            //int vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
            int vehicle_id = (Integer)request.getSession().getAttribute("vehicle_id");
            switch(get) {
                case "delete_alert":
                    alert_id = Integer.parseInt(request.getParameter("alert_id"));
                    //vehicle_id = logic.getVehicleIdByAlertId(alert_id);
                    logic.deleteMaintAlert(alert_id);
                    List<MaintenanceAlertDTO> maintAlerts = logic.getMaintAlertByUserId_VehicleId(vehicle_id, user_id);
                    request.setAttribute("maint_alerts", maintAlerts);
                    List<VehicleDTO> vehicles = logic.getVehiclesByUserId(user_id);
                    request.setAttribute("vehicles", vehicles);
                    request.setAttribute("cur_vehicle", vehicle_id);
                    request.getRequestDispatcher("/views/operator/OperatorAlertView.jsp").forward(request, response);
                    break;
                case "update_alert":
                    alert_id = Integer.parseInt(request.getParameter("alert_id"));
                    //vehicle_id = logic.getVehicleIdByAlertId(alert_id);
                    request.setAttribute("components", logic.getComponentsByVehicleId(vehicle_id));
                    request.setAttribute("cur_vehicle", logic.getVehicleByVehicleId(vehicle_id).getVehicleNumber());
                    //MaintenanceAlertDTO malert = logic.getAlertByMaintAlertId(alert_id);
                    //int alertTypeid = malert.getAlertId();
                    request.setAttribute("cur_alert", logic.getAlertByMaintAlertId(alert_id));
                    request.setAttribute("alert_types", logic.getAllAlertTypes());
                    request.getRequestDispatcher("/views/operator/OperatorAlertUpdateView.jsp").forward(request, response);
                    break;
                /*case "new_alert":
                    //vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
                    request.setAttribute("cur_vehicle", logic.getVehicleByVehicleId(vehicle_id).getVehicleNumber());
                    request.setAttribute("components", logic.getComponentsByVehicleId(vehicle_id));
                    request.setAttribute("alert_types", logic.getAllAlertTypes());
                    request.getRequestDispatcher("/views/operator/OperatorAlertAddView.jsp").forward(request, response);
                    break;*/
                default:
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String action = request.getParameter("action");
            int vehicle_id;
            int user_id = (Integer)request.getSession().getAttribute("user_id");
            List<VehicleDTO> vehicles = logic.getVehiclesByUserId(user_id);
            switch(action) {
                case "Load":
                    vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
                    request.getSession().setAttribute("vehicle_id", vehicle_id);
                    List<MaintenanceAlertDTO> maintAlerts = logic.getMaintAlertByUserId_VehicleId(vehicle_id, user_id);
                    request.setAttribute("maint_alerts", maintAlerts);
                    request.setAttribute("vehicles", vehicles);
                    request.setAttribute("cur_vehicle", vehicle_id);
                    request.getRequestDispatcher("/views/operator/OperatorAlertView.jsp").forward(request, response);
                    break;
                case "Report New Alert":
                    vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
                    request.getSession().setAttribute("vehicle_id", vehicle_id);
                    request.setAttribute("cur_vehicle", logic.getVehicleByVehicleId(vehicle_id).getVehicleNumber());
                    request.setAttribute("components", logic.getComponentsByVehicleId(vehicle_id));
                    request.setAttribute("alert_types", logic.getAllAlertTypes());
                    request.getRequestDispatcher("/views/operator/OperatorAlertAddView.jsp").forward(request, response);
                    break;
                case "Add Alert":
                    //vehicle_id = (Integer)request.getSession().getAttribute("vehicle_id");
                    int alert_id = Integer.parseInt(request.getParameter("alert_type"));
                    int component_id = Integer.parseInt(request.getParameter("component_id"));
                    java.sql.Date alert_date = java.sql.Date.valueOf(request.getParameter("alert_date"));
                    MaintenanceAlertDTO malert = new MaintenanceAlertDTO();
                    malert.setAlertId(alert_id);
                    malert.setComponentId(component_id);
                    malert.setAlertDate(alert_date);
                    malert.setReporterId(user_id);
                    malert.setResolved(false);
                    if(logic.addMaintAlert(malert))
                    {
                        request.setAttribute("vehicles", vehicles);
                        request.getRequestDispatcher("/views/operator/OperatorAlertView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Report Maintenance Alert Fail.");
                            request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                case "Update Alert":
                    //vehicle_id = (Integer)request.getSession().getAttribute("vehicle_id");
                    int malertid = Integer.parseInt(request.getParameter("malert_id"));
                    int alertid = Integer.parseInt(request.getParameter("alert_type"));
                    int componentid = Integer.parseInt(request.getParameter("component_id"));
                    String date = request.getParameter("alert_date");
                    java.sql.Date alertdate = java.sql.Date.valueOf(date);
                    MaintenanceAlertDTO maintalert = new MaintenanceAlertDTO();
                    maintalert.setMaintenanceId(malertid);
                    maintalert.setAlertId(alertid);
                    maintalert.setComponentId(componentid);
                    maintalert.setAlertDate(alertdate);
                    maintalert.setReporterId(user_id);
                    maintalert.setResolved(false);
                    if(logic.updateMaintAlert(maintalert))
                    {
                        request.setAttribute("vehicles", vehicles);
                        request.getRequestDispatcher("/views/operator/OperatorAlertView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Update Maintenance Alert Fail.");
                            request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                default:
                    break;
            } 
        }catch (Exception ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
