package controller;

import business.AlertTypeBusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * Servlet responsible for handling deletion of alert types from the system.
 * <p>
 * This servlet accepts an `id` parameter via GET request and attempts to delete
 * the corresponding alert type using the {@link AlertTypeBusinessLogic} class.
 * After deletion, it redirects back to the alert type management view.
 * </p>
 *
 * URL pattern: <code>/DeleteAlertType</code>
 * 
 * Example usage:
 * <code>/DeleteAlertType?id=5</code> will delete the alert with ID 5.
 * 
 * Error handling:
 * - If the `id` is missing or invalid, it redirects to the alert list with an error message.
 * - If deletion fails, it sets an error attribute (not shown in this version but can be used on redirected page).
 * 
 * @author 
 */
@WebServlet(name = "DeleteAlertTypeServlet", urlPatterns = {"/DeleteAlertType"})
public class DeleteAlertTypeServlet extends HttpServlet {

    /**
     * Handles the HTTP GET method to delete an alert type by ID.
     *
     * @param request  The HttpServletRequest containing the alert ID as a parameter.
     * @param response The HttpServletResponse used for redirection.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int alertId = Integer.parseInt(idParam);
                AlertTypeBusinessLogic logic = new AlertTypeBusinessLogic();
                boolean deleted = logic.deleteAlert(alertId);

                if (deleted) {
                    response.sendRedirect(request.getContextPath() + "/Manager?get=alert_type");
                    return;
                } else {
                    request.setAttribute("error", "Failed to delete alert type.");
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Alert ID.");
            } catch (Exception e) {
                request.setAttribute("error", "Error: " + e.getMessage());
            }
        }

        // Fallback redirect (including when id is null or deletion fails)
        response.sendRedirect(request.getContextPath() + "/Manager?get=alert_type");
    }
}
