package controller;

import business.AlertTypeBusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.AlertDTO;

import java.io.IOException;

@WebServlet(name = "EditAlertTypeServlet", urlPatterns = {"/EditAlertType"})
public class EditAlertTypeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the alert ID from the query string
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int alertId = Integer.parseInt(idParam);
                AlertTypeBusinessLogic logic = new AlertTypeBusinessLogic();
                AlertDTO alert = logic.getAlertById(alertId);

                if (alert != null) {
                    request.setAttribute("alert", alert);
                    request.getRequestDispatcher("/views/manager/EditAlertType.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // Invalid ID format, handle below
            }
        }

        // If error, redirect back to alert list
        response.sendRedirect(request.getContextPath() + "/Manager?get=alert_type");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("alert_id"));
            String type = request.getParameter("alert_type");
            String description = request.getParameter("alert_description");

            AlertDTO alert = new AlertDTO();
            alert.setAlertId(id);
            alert.setAlertType(type);
            alert.setAlertDescription(description);

            AlertTypeBusinessLogic logic = new AlertTypeBusinessLogic();
            boolean success = logic.updateAlert(alert);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/Manager?get=alert_type");
            } else {
                request.setAttribute("error", "Failed to update alert type.");
                request.setAttribute("alert", alert); // so user doesn't lose their input
                request.getRequestDispatcher("/views/manager/EditAlertType.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid alert ID.");
            request.getRequestDispatcher("/views/manager/EditAlertType.jsp").forward(request, response);
        }
    }
}
