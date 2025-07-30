/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;



import business.AlertTypeBusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.AlertDTO;

import java.io.IOException;

@WebServlet(name = "AddAlertTypeServlet", urlPatterns = {"/AddAlertType"})
public class AddAlertTypeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("alert_type");
        String description = request.getParameter("alert_description");

        AlertDTO newAlert = new AlertDTO();
        newAlert.setAlertType(type);
        newAlert.setAlertDescription(description);

        AlertTypeBusinessLogic logic = new AlertTypeBusinessLogic();
        boolean success = logic.addAlert(newAlert);

        if (success) {
            // Redirect back to alert list
            response.sendRedirect(request.getContextPath() + "/Manager?get=alert_type");
        } else {
            // Handle failure (optional)
            request.setAttribute("error", "Failed to add alert type.");
            request.getRequestDispatcher("/views/manager/AddAlertType.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Direct GET requests to the form page
        request.getRequestDispatcher("/views/manager/AddAlertType.jsp").forward(request, response);
    }
}

