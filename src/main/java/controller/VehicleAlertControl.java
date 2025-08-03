package controller;
/*
 Student Name: Xiaoli He, Shan Cai, Yanqi Huang
 Project Name: Tranisit Fleet Management
 Section: CST8288 Section 024
 Due Date: Aug 08, 2025
*/
import business.VehicleAlertBusinessLogic;
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

import model.VehicleAlertDTO;
/**
 * VehicleAlertControl is a servlet that handles the retrieval and display
 * of vehicle alert records associated with a specific user (operator).
 * @author Xiaoli He, Shan Cai, Yanqi Huang
 * @since July 20,2025
 */
@WebServlet(name = "VehicleAlertControl", urlPatterns = {"/VehicleAlertControl"})
public class VehicleAlertControl extends HttpServlet {
    /** Business logic object to handle vehicle alert retrieval */
    private VehicleAlertBusinessLogic vablogic = new VehicleAlertBusinessLogic();
     /**
     * Handles HTTP GET requests by retrieving alerts for the current user.
     * 
     * @param request  the HttpServletRequest containing the session and parameters
     * @param response the HttpServletResponse used to forward to JSP
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        try {
            int userId = (Integer) request.getSession().getAttribute("user_id");
            List<VehicleAlertDTO> alerts = vablogic.getObjById(userId);
            request.setAttribute("alerts", alerts);
            request.getRequestDispatcher("/views/operator/InformationCheck/VehicleAlert/VehicleAlertView.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VehicleAlertControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}