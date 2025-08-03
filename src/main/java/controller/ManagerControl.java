/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.MaintenanceAlertDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OperatorPerformanceDTO;
import dao.OperatorPerformanceDAO;
import model.MaintenanceAlertDTO;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "ManagerControl", urlPatterns = {"/Manager"})
public class ManagerControl extends HttpServlet {

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
            switch (get) {
                case "alert_type":
                    //getAlertTypes, list, add, delete, update
                    request.getRequestDispatcher("/views/manager/AlertTypeView.jsp").forward(request, response);
                    break;
                case "all_alerts":
                    //getActiveAlerts, list, update resolved
                    request.getRequestDispatcher("/views/manager/ActiveAlertView.jsp").forward(request, response);
                    break;
                case "maintenance_schedule":
                    //getActiveMaintenanceSchedule, list, add, delete, update
                    request.getRequestDispatcher("/views/manager/ActiveMaintScheduleView.jsp").forward(request, response);
                    break;
                case "operator_performance":
                    //getPerformance, list, sort, filtereByOperatorName
                    //private OperatorPerformanceBusinessLogic logic = new OperatorPerformanceBusinessLogic();
                    //List<OperatorPerformanceDTO> performanceList = logic.getPerformanceForOperator(userId);
                    //request.setAttribute("performanceList", performanceList);
                    OperatorPerformanceDAO opDAO = new OperatorPerformanceDAO();
                    List<OperatorPerformanceDTO> logs = opDAO.getAllOperatorPerformanceLogs();
                    request.setAttribute("logs", logs);
                    request.getRequestDispatcher("/views/manager/OperatorPerformanceView.jsp").forward(request, response);
                    break;
                case "maintenance_report":
                    //getAllMaintenanceSchedule, list, sort
                    MaintenanceAlertDAO dao = new MaintenanceAlertDAO();
                    List<MaintenanceAlertDTO> alerts = dao.getMaintenanceAlertReport();
                    request.setAttribute("alerts", alerts);
                    request.getRequestDispatcher("/views/manager/MaintenanceAlertList.jsp").forward(request, response);
                    break;
                case "maintenance_cost":

                    //getAllCostOnMaintenance, list, group by alert type, vehicle, component
                    MaintenanceAlertDAO cdao = new MaintenanceAlertDAO();
                    List<MaintenanceAlertDTO> list = cdao.getMaintenanceCostReport();
                    request.setAttribute("costs", list);
                    request.getRequestDispatcher("/views/manager/AllCostOnMaintView.jsp").forward(request, response);
                    break;
                case "fuel_energy_cost":
                    //getAllCostOnFuelEnergy, list, group by vehicle type, vehicle
                    request.getRequestDispatcher("/views/manager/AllCostOnFuelEnergyView.jsp").forward(request, response);
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
