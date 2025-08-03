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

@WebServlet(name = "OperatorPerformanceListServlet", urlPatterns = {"/operatorPerformanceList"})

public class OperatorPerformanceListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OperatorPerformanceDAO opDAO = new OperatorPerformanceDAO();
     
        List<OperatorPerformanceDTO> logs = opDAO.getAllOperatorPerformanceLogs();
//        List<OperatorPerformanceDTO> logs = opDAO.getAllOperatorPerformanceLogs();

        request.setAttribute("logs", logs);

        System.out.println("logs size: " + (logs == null ? "null" : logs.size()));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/manager/OperatorPerformanceList.jsp");
        dispatcher.forward(request, response);
    }
}
