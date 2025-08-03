package controller;

import business.AlertTypeBusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.AlertDTO;

import java.io.IOException;

/**
 * Servlet that handles editing of alert types.
 * 
 * This servlet supports both GET and POST requests:
 * <ul>
 *     <li><b>GET</b>: Loads an existing alert type for editing using its ID.</li>
 *     <li><b>POST</b>: Updates the alert type in the database with the submitted changes.</li>
 * </ul>
 * 
 * URL Pattern: <code>/EditAlertType</code>
 * 
 * On success, redirects back to the alert type list.
 * On failure, forwards back to the edit form with error messages.
 * 
 * @author 
 */
@WebServlet(name = "EditAlertTypeServlet", urlPatterns = {"/EditAlertType"})
public class EditAlertTypeServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     * Retrieves the alert type details by ID and forwards to the edit form.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                // Invalid ID format
            }
        }

        // Redirect to alert list if ID is invalid or alert not found
        response.sendRedirect(request.getContextPath() + "/Manager?get=alert_type");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Receives updated alert type data and persists the changes.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                request.setAttribute("alert", alert);
                request.getRequestDispatcher("/views/manager/EditAlertType.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid alert ID.");
            request.getRequestDispatcher("/views/manager/EditAlertType.jsp").forward(request, response);
        }
    }
}
