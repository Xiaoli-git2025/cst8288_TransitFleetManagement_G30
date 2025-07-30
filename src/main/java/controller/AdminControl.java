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
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import business.*;
import model.*;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "AdminControl", urlPatterns = {"/Admin"})
public class AdminControl extends HttpServlet {

    /**
     * business logic instance
     */
    private RouteBusinessLogic logic;
    private VehicleComponentBusinessLogic vclogic;
    /**
     * init method
     */
    @Override
    public void init() {
        logic = new RouteBusinessLogic();
        vclogic = new VehicleComponentBusinessLogic();
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
            switch(get) {
                case "route":
                    List<RouteDTO> routes = logic.getAllObjects();
                    request.setAttribute("routes", routes);
                    request.getRequestDispatcher("/views/admin/RouteView.jsp").forward(request, response);
                    break;
                case "station":
                    //getAllStations, list, add, delete, update
                    request.getRequestDispatcher("/views/admin/StationView.jsp").forward(request, response);
                    break;
                case "route_schedule":
                    //getAllRouteSchedules, list add, delete, uodate
                    request.getRequestDispatcher("/views/admin/RouteScheduleView.jsp").forward(request, response);
                    break;
                case "vehicle":
                    //getAllVehicles, list ,add, delete, update
                    request.getRequestDispatcher("/views/admin/VehicleView.jsp").forward(request, response);
                    break;
                case "component":
                    //getAllComponents, list, add, delete, update
                    List<VehicleComponentDTO> vehicleComponent = vclogic.getAllObjects();
                    request.setAttribute("vehicleComponents", vehicleComponent);
                    request.getRequestDispatcher("/views/admin/VehicleComponent.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (SQLException ex) {
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
