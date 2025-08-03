<%-- 
    Document   : AdminDashBoard
    Created on : Jul 23, 2025, 3:08:46 PM
    Author     : Xiaoli He
--%>
<%@page import="business.RouteBusinessLogic"%>
<%@page import="java.util.List" %>
<%@page import="model.RouteDTO" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RouteDTO> routes = (List<RouteDTO>) request.getAttribute("routes");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>Transit Fleet Management System - Route</title>
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Admin - Route Management</h1>
            <div class="header-buttons">
                <button class="icon-btn" onclick="history.back()" title="Back">
                    <i class="fas fa-arrow-left"></i>
                </button>
                <a href="${pageContext.request.contextPath}/Login?logout=true" class="icon-btn logout" title="Logout">
                    <i class="fas fa-sign-out-alt"></i>
                </a>
            </div>
        </div>

        <!-- Main content with sidebar -->
        <div class="main">
            <!-- Sidebar -->
            <nav class="sidebar">
                <ul class="menu">
                    <li class="menu-item">
                        <span class="menu-title">Route Management</span>
                        <ul class="submenu">
                            <li><a href="${pageContext.request.contextPath}/Admin?get=route">Route</a></li>
                            <li><a href="${pageContext.request.contextPath}/Admin?get=station">Station</a></li>
                            <li><a href="${pageContext.request.contextPath}/Admin?get=route_schedule">Route Schedule</a></li>
                        </ul>
                    </li>
                    <li class="menu-item">
                        <span class="menu-title">Vehicle Management</span>
                        <ul class="submenu">
                            <li><a href="${pageContext.request.contextPath}/Admin?get=vehicle">Vehicle</a></li>
                            <li><a href="${pageContext.request.contextPath}/Admin?get=component">Vehicle Component</a></li>
                            <li><a href="${pageContext.request.contextPath}/Admin?get=fuel">Fuel Consumption</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>

            <!-- Main container -->
            <div class="container">
                <div class="box_with_menu">
                    <div style="text-align:right; margin-bottom:1rem;">
                        <a href="${pageContext.request.contextPath}/views/admin/AddRoute.jsp"
                           class="btn btn-outline-primary">Add Route</a>
                    </div>

                    <table border="1">
                        <thead>
                            <tr>
                                <th>Route Id</th>
                                <th>Route Number</th>
                                <th>Description</th>
                                <th style="width:200px;">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (RouteDTO route : routes) {%>
                            <tr>
                                <td><%= route.getRouteId()%></td>
                                <td><%= route.getRouteNumber()%></td>
                                <td><%= route.getDescription()%></td>
                                <td>
                                    <a href="<%= request.getContextPath()%>/views/admin/EditRoute.jsp?id=<%= route.getRouteId()%>"
                                       class="btn btn-sm btn-primary">Edit</a>
                                    <a href="<%= request.getContextPath()%>/RouteControl?action=DeleteRoute&routeId=<%= route.getRouteId()%>"
                                       class="btn btn-sm btn-danger" 
                                       onclick="return confirm('Are you sure you want to delete this component?')">Delete</a>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p>Contact us at: tfms@algonquinlive.com | Phone: (123) 456-7890 | 
                <a href="linkedin.html">LinkedIn</a>
            </p>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const menuItems = document.querySelectorAll(".menu-item .menu-title");

                menuItems.forEach(function (menuTitle) {
                    menuTitle.addEventListener("click", function () {
                        const submenu = this.nextElementSibling;
                        submenu.classList.toggle("show");
                    });
                });
            });
        </script>
    </body>
</html>
