package controller;

import dao.MaintenanceAlertDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.MaintenanceAlertDTO;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MaintenanceCostReport", urlPatterns = {"/MaintenanceCostReport"})

public class MaintenanceCostReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MaintenanceAlertDAO dao = new MaintenanceAlertDAO();
        List<MaintenanceAlertDTO> list = dao.getMaintenanceCostReport();
        request.setAttribute("costs", list);
        request.getRequestDispatcher("/AllCostOnMaintView.jsp").forward(request, response);
    }
}
