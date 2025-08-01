package controller;

import business.RouteBusinessLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RouteDTO;

@WebServlet(name = "RouteControl", urlPatterns = {"/RouteControl"})
public class RouteControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public RouteBusinessLogic rblogic;

    @Override
    public void init() throws ServletException {
        rblogic = new RouteBusinessLogic();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                getAllRoutes(request, response);
                return;
            }

            switch (action) {
                case "AddRoute":  
                    addRoute(request, response);
                    break;
                case "UpdateRoute": 
                    updateRoute(request, response);
                    break;              
                case "GetAllRoutes": 
                    getAllRoutes(request, response);
                    break;
                default:
                    getAllRoutes(request, response);  
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            switch (action) {
                case "DeleteRoute":
                        deleteRoute(request, response);
                        break;
                default:
                    getAllRoutes(request,response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RouteControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void getAllRoutes(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        List<RouteDTO> allRoutes = rblogic.getAllObjects();
        if (allRoutes == null) {
            allRoutes = new ArrayList<>();
            Logger.getLogger(RouteControl.class.getName())
                .log(Level.WARNING, "return null");
        }
        request.setAttribute("routes", allRoutes);
        request.setAttribute("msg", request.getParameter("msg"));
        request.getRequestDispatcher("/views/admin/RouteView.jsp").forward(request, response);
    }

    private void addRoute(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        RouteDTO route = new RouteDTO();
        route.setRouteNumber(Integer.parseInt(request.getParameter("route_number")));
        route.setDescription(request.getParameter("description"));
        boolean success = rblogic.addObject(route);
        if (success) {
            getAllRoutes(request,response);       
        } else {
            request.setAttribute("error", "Failed to add route");
            request.getRequestDispatcher("/views/admin/AddRoute.jsp").forward(request, response);
        }
    }

    private void updateRoute(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        RouteDTO route = new RouteDTO();
        route.setRouteId(Integer.parseInt(request.getParameter("route_id")));
        route.setRouteNumber(Integer.parseInt(request.getParameter("route_number")));
        route.setDescription(request.getParameter("description"));
        boolean success = rblogic.updateObject(route);
        if (success) {
            getAllRoutes(request,response);
        } else {
            request.setAttribute("error", "Failed to update route");
            request.getRequestDispatcher("/views/admin/EditRoute.jsp").forward(request, response);
        }
    }

    private void deleteRoute(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int routeId = Integer.parseInt(request.getParameter("routeId"));
        boolean success = rblogic.deleteObject(routeId);
        if (success) {
            getAllRoutes(request,response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}