package controller;

import dao.OperatorPerformanceDAO;
import dao.StationTimeDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.OperatorPerformanceDTO;

/**
 * OperatorPerformanceListServlet is a servlet responsible for retrieving and displaying
 * a list of operator performance logs.
 *
 * @author 
 * @version 1.0
 * @since 2025-08-03
 */

@WebServlet(name = "OperatorPerformanceListServlet", urlPatterns = {"/operatorPerformanceList"})
public class OperatorPerformanceListServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method by retrieving all operator performance logs
     * and forwarding the data to the JSP view.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OperatorPerformanceDAO opDAO = new OperatorPerformanceDAO();
     
        // Retrieve all operator performance logs
        List<OperatorPerformanceDTO> logs = opDAO.getAllOperatorPerformanceLogs();

        // Set logs in request scope for JSP rendering
        request.setAttribute("logs", logs);

        // Debug output
        System.out.println("logs size: " + (logs == null ? "null" : logs.size()));

        // Forward to JSP for display
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/manager/OperatorPerformanceList.jsp");
        dispatcher.forward(request, response);
    }
}
