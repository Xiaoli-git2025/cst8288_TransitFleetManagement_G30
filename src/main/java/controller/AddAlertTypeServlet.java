package controller;

import business.AlertTypeBusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.AlertDTO;

import java.io.IOException;

/**
 * Servlet responsible for handling the addition of new alert types.
 * 
 * This servlet handles both GET and POST requests:
 * - GET: Displays the alert type form.
 * - POST: Processes form submissions to create a new alert type.
 * 
 * Mapped to the URL pattern: /AddAlertType
 * 
 * @author 
 */
@WebServlet(name = "AddAlertTypeServlet", urlPatterns = {"/AddAlertType"})
public class AddAlertTypeServlet extends HttpServlet {

    /**
     * Handles HTTP POST requests to add a new alert type.
     * 
     * This method retrieves the alert type and description from the request,
     * creates a new {@link AlertDTO}, and uses {@link AlertTypeBusinessLogic}
     * to add the alert to the system.
     * 
     * On success, it redirects the user back to the alert type list.
     * On failure, it forwards back to the form with an error message.
     *
     * @param request  the {@link HttpServletRequest} object containing the form data
     * @param response the {@link HttpServletResponse} used to redirect or forward
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs during processing
     */
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
            // Handle failure
            request.setAttribute("error", "Failed to add alert type.");
            request.getRequestDispatcher("/views/manager/AddAlertType.jsp").forward(request, response);
        }
    }

    /**
     * Handles HTTP GET requests to display the form for adding a new alert type.
     *
     * This method simply forwards the request to the AddAlertType.jsp form.
     *
     * @param request  the {@link HttpServletRequest}
     * @param response the {@link HttpServletResponse}
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs during forwarding
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/manager/AddAlertType.jsp").forward(request, response);
    }
}
