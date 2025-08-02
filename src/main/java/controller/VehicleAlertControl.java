package controller;
import business.VehicleAlertBusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.VehicleAlertDTO;

@WebServlet(name = "VehicleAlertControl", urlPatterns = {"/VehicleAlertControl"})
public class VehicleAlertControl extends HttpServlet {
    private VehicleAlertBusinessLogic vablogic = new VehicleAlertBusinessLogic();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        try {
            int userId = (Integer) request.getSession().getAttribute("user_id");
            List<VehicleAlertDTO> alerts = vablogic.getObjById(userId);
            request.setAttribute("alerts", alerts);
            request.getRequestDispatcher("/views/operator/InformationCheck/VehicleAlert/VehicleAlertView.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VehicleAlertControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}