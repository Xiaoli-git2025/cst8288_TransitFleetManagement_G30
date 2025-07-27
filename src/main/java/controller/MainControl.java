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
import model.RouteDTO;
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
                    switch(login_register_logic.checkAccount(request.getParameter("email"), request.getParameter("password"))){
                        case "not_found":
                            request.setAttribute("errorMsg", "User not found, please register firstly");
                            request.getRequestDispatcher("/error.jsp").forward(request, response);
                            break;
                        case "operator":
                            request.getRequestDispatcher("/views/operator/OperatorDashboard.jsp").forward(request, response);
                            break;
                        case "manager":
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
                    String role = request.getParameter("role");
                    int route_id = Integer.parseInt(request.getParameter("route_id"));
                    if(login_register_logic.AddUser(email, name, password, role, route_id))
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
