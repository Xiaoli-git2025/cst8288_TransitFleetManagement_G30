package controller;

import business.AlertTypeBusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "DeleteAlertTypeServlet", urlPatterns = {"/DeleteAlertType"})
public class DeleteAlertTypeServlet extends HttpServlet {

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

        // Fallback
        response.sendRedirect(request.getContextPath() + "/Manager?get=alert_type");
    }
}
