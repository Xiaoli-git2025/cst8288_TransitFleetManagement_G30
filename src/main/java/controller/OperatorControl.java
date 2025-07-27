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
import model.RouteDTO;
import business.*;
import model.*;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "OperatorControl", urlPatterns = {"/Operator"})
public class OperatorControl extends HttpServlet {

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
            switch(get) {
                case "stop_time":
                    //getCurrentDateStationTime, list, add, delete, update
                    request.getRequestDispatcher("/views/operator/StationView.jsp").forward(request, response);
                case "fuel_consumption":
                    //getFuelConsumption, list, add, delete, update 
                    request.getRequestDispatcher("/views/operator/FuelView.jsp").forward(request, response);
                case "operator_alert":
                    //getCurrentOperatorSeletedVehicleAlert, list, add, delete, update
                    request.getRequestDispatcher("/views/operator/OperatorAlertView.jsp").forward(request, response);
                case "vehicle_alert":
                    //getSelectedVehicleAlert, list, can sort resolved and alert date
                    request.getRequestDispatcher("/views/operator/VehicleAlertView.jsp").forward(request, response);
                case "maintance_schedule":
                    //getSelectedVehicleMaintenanceRecord, list, sort
                    request.getRequestDispatcher("/views/operator/ComponentView.jsp").forward(request, response);
                case "operator_performance":
                    //getCurrentOperatorPerformance
                    request.getRequestDispatcher("/views/operator/OperatorPerformanceView.jsp").forward(request, response);
                default:
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
