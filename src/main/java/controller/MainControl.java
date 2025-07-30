/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import business.*;
import business.alert_maint.*;
import dao.*;
import model.*;
import java.util.List;

/**
 *
 * @author Xiaoli He
 */
@WebServlet(name = "MainControl", urlPatterns = {"/Login"})
public class MainControl extends HttpServlet {

    /**
     * business logic instance
     */
    private LoginRegisterBusinessLogic login_register_logic;
    /**
     * init method
     */
    @Override
    public void init() {
        login_register_logic = new LoginRegisterBusinessLogic();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String logout = request.getParameter("logout");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        try{
            String action = request.getParameter("action");
            switch(action) {
                case "Login":
                    String role = login_register_logic.checkAccount(request.getParameter("email"), request.getParameter("password"));
                    String[] roles = role.split(",");
                    role = roles[0];
                    int user_id = roles.length > 1 ? Integer.parseInt(roles[1]) : 0;
                    request.getSession().setAttribute("user_id", user_id);
                    switch(role){
                        case "not_found":
                            request.setAttribute("errorMsg", "User not found, please register firstly");
                            request.getRequestDispatcher("/error.jsp").forward(request, response);
                            break;
                        case "operator":
                            request.getRequestDispatcher("/views/operator/OperatorDashboard.jsp").forward(request, response);
                            break;
                        case "manager":
                            //setup observer
                            MaintenanceAlertDAO alertDAO = new MaintenanceAlertDAO();
                            AlertObserver alertObserver = new AlertObserver(alertDAO);
                            MaintenanceSubject subject = new MaintenanceSubject();
                            subject.addObserver(alertObserver);
                            request.getSession().setAttribute("maintenanceSubject", subject);
                            request.getRequestDispatcher("/views/manager/ManagerDashboard.jsp").forward(request, response);
                            break;
                        default:
                            request.getRequestDispatcher("/views/admin/AdminDashboard.jsp").forward(request, response);
                            break;
                    }
                    break;

                case "Register":
                    RouteBusinessLogic route_logic = new RouteBusinessLogic();
                    List<RouteDTO> routes = route_logic.getAllObjects();
                    request.setAttribute("routes", routes);
                    request.getRequestDispatcher("/views/Register.jsp").forward(request, response);
                    break;
                case "Register Me":
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String user_type = request.getParameter("role");
                    int route_id = Integer.parseInt(request.getParameter("route_id"));
                    if(login_register_logic.AddUser(email, name, password, user_type, route_id))
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    else{
                        request.setAttribute("errorMsg", "Register fail");
                            request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
