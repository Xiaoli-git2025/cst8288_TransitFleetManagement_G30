/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import business.*;
import model.*;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "OperatorControl", urlPatterns = {"/Operator"})
public class OperatorControl extends HttpServlet {

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
            switch(get) {
                case "stop_time":
                    //getCurrentDateStationTime, list, add, delete, update
                    request.getRequestDispatcher("/views/operator/StationView.jsp").forward(request, response);
                    break;
                case "fuel_consumption":
                    //getFuelConsumption, list, add, delete, update 
                    request.getRequestDispatcher("/views/operator/FuelView.jsp").forward(request, response);
                    break;
                case "operator_alert":
                    //getCurrentOperatorSeletedVehicleAlert, list, add, delete, update
                    List<VehicleDTO> vehicles = logic.getVehiclesByUserId(user_id);
                    request.setAttribute("vehicles", vehicles);
                    request.getRequestDispatcher("/views/operator/OperatorAlertView.jsp").forward(request, response);
                    break;
                case "vehicle_alert":
                    //getSelectedVehicleAlert, list, can sort resolved and alert date
                    request.getRequestDispatcher("/views/operator/VehicleAlertView.jsp").forward(request, response);
                    break;
                case "maintance_schedule":
                    //getSelectedVehicleMaintenanceRecord, list, sort
                    request.getRequestDispatcher("/views/operator/ComponentView.jsp").forward(request, response);
                    break;
                case "operator_performance":
                    //getCurrentOperatorPerformance
                    request.getRequestDispatcher("/views/operator/OperatorPerformanceView.jsp").forward(request, response);
                    break;
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
        /*try{
            String action = request.getParameter("action");
            int vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
            request.getSession().setAttribute("vehicle_id", vehicle_id);
            int user_id = (Integer)request.getSession().getAttribute("user_id");
            if(action.equals("Load")){
                List<MaintenanceAlertDTO> maintAlerts = logic.getMaintAlertByUserId_VehicleId(vehicle_id, user_id);
                request.setAttribute("maint_alerts", maintAlerts);
                List<VehicleDTO> vehicles = logic.getVehiclesByUserId(user_id);
                request.setAttribute("vehicles", vehicles);
                request.setAttribute("cur_vehicle", vehicle_id);
                request.getRequestDispatcher("/views/operator/OperatorAlertView.jsp").forward(request, response);
            }  
        }catch (Exception ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
