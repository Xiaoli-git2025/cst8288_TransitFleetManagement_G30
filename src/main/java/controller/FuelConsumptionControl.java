/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import business.AlertBusinessLogic;
import business.StopTimeBusinessLogic;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RouteScheduleDTO;
import model.StationTimeDTO;
import business.FuelConsumptionBusinessLogic;
import java.math.BigDecimal;
import model.FuelConsumptionDTO;
import model.VehicleDTO;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "FuelConsumptionControl", urlPatterns = {"/FuelConsumption"})
public class FuelConsumptionControl extends HttpServlet {

    /**
     * business logic instance
     */
    private FuelConsumptionBusinessLogic logic;
    private AlertBusinessLogic blogic;
    /**
     * init method
     */
    @Override
    public void init() {
        logic = new FuelConsumptionBusinessLogic();
        blogic = new AlertBusinessLogic();
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
            //int vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
            int fc_id = 0;
            switch(get) {
                case "log_update":
                    fc_id = Integer.parseInt(request.getParameter("fc_id"));
                    FuelConsumptionDTO fc = logic.getFuelConsumptionById(fc_id);
                    request.setAttribute("cur_fuel", fc);
                    request.getRequestDispatcher("/views/operator/FuelConsumptionUpdateView.jsp").forward(request, response);
                    break;
                case "log_delete":
                    fc_id = Integer.parseInt(request.getParameter("fc_id"));
                    if(logic.deleteFuelConsumptionById(fc_id)){
                        List<VehicleDTO> vehicle = blogic.getVehiclesByUserId(user_id);
                        request.setAttribute("vehicles", vehicle);
                        request.getRequestDispatcher("/views/operator/FuelConsumptionView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Delete Fuel Consumption Fail.");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
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
        try{
            String action = request.getParameter("action");
            int user_id = (Integer)request.getSession().getAttribute("user_id");
            List<VehicleDTO> vehicles = blogic.getVehiclesByUserId(user_id);
            
            int vehicle_id = 0;
            java.sql.Date log_date = java.sql.Date.valueOf(LocalDate.now());
            switch(action) {
                case "Load Fuel Consumption":
                    vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
                    //request.getSession().setAttribute("vehicle_id", vehicle_id);
                    List<FuelConsumptionDTO> fules = logic.getFuelConsumptionsByVehicleId(vehicle_id);
                    request.setAttribute("fuel_consumptions", fules);
                    request.setAttribute("vehicles", vehicles);
                    //request.setAttribute("vehicle_id", vehicle_id);
                    request.getRequestDispatcher("/views/operator/FuelConsumptionView.jsp").forward(request, response);
                    break;
                case "Log New Fuel Consumption":
                    vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
                    request.getSession().setAttribute("vehicle_id", vehicle_id);
                    //request.setAttribute("vehicle_id", vehicle_id);
                    request.getRequestDispatcher("/views/operator/FuelConsumptionAddView.jsp").forward(request, response);
                    break;
                case "Log Fuel Consumption":                
                    FuelConsumptionDTO fuel = new FuelConsumptionDTO();
                    vehicle_id = Integer.parseInt(request.getParameter("vehicle_id"));
                    //vehicle_id = (Integer)request.getSession().getAttribute("vehicle_id");
                    fuel.setVehicleId(vehicle_id);
                    fuel.setDate(log_date);
                    fuel.setMilesTraveled(new BigDecimal(request.getParameter("miles_traveled")));
                    fuel.setUnitPrice(new BigDecimal(request.getParameter("unit_price")));
                    
                    if(logic.LogFuelConsumption(fuel))
                    {
                        request.setAttribute("vehicles", vehicles);
                        request.getRequestDispatcher("/views/operator/FuelConsumptionView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Log Fuel Consumption Fail.");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                case "Update Fuel Consumption":
                    FuelConsumptionDTO ufuel = new FuelConsumptionDTO();
                    ufuel.setFcId(Integer.parseInt(request.getParameter("fc_id")));
                    ufuel.setVehicleId(Integer.parseInt(request.getParameter("vehicle_id")));
                    String dateStr = request.getParameter("date");
                    java.sql.Date sqlDate = java.sql.Date.valueOf(dateStr);
                    ufuel.setDate(sqlDate);
                    ufuel.setMilesTraveled(new BigDecimal(request.getParameter("miles_traveled")));
                    ufuel.setUnitPrice(new BigDecimal(request.getParameter("unit_price")));
                    
                    if(logic.UpdateFuelConsumption(ufuel))
                    {
                        //List<VehicleDTO> vehicle = blogic.getVehiclesByUserId(user_id);
                        request.setAttribute("vehicles", vehicles);
                        request.getRequestDispatcher("/views/operator/FuelConsumptionView.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("errorMsg", "Update Fuel Consumption Fail.");
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
