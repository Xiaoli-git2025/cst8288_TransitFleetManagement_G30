package controller;

import dao.MaintenanceAlertDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.MaintenanceAlertDTO;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MaintenanceAlertListServlet", urlPatterns = {"/maintenanceAlertList"})
public class MaintenanceAlertListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        MaintenanceAlertDAO dao = new MaintenanceAlertDAO();
        List<MaintenanceAlertDTO> alerts = dao.getMaintenanceAlertReport();
        
        request.setAttribute("alerts", alerts);
        request.getRequestDispatcher("/views/manager/MaintenanceAlertList.jsp").forward(request, response);
    }
}
