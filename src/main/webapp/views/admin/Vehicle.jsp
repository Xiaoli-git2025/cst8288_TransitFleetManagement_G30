<%-- 
    Document   : Vehicle
    Created on : Jul 31, 2025, 2:11:33 p.m.
    Author     : shano
--%>

<%@page import="java.util.List"%>
<%@page import="model.VehicleDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>Transit Fleet Management System - Vehicle</title>
       
        <!--
        <style>
            .vehicle-component-table th,
            .vehicle-component-table td {
               word-break: break-word; 
               padding: 8px 12px; 
            }
        </style>
        -->
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Vehicle</h1>
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
                        </ul>
                    </li>
                </ul>
            </nav>
            <div class="container">
                <div class="box_with_menu">
                    <div style="text-align:right; margin-bottom:1rem;">
                        <a href="${pageContext.request.contextPath}/views/admin/AddVehicle.jsp"
                           class="btn btn-outline-primary">Add Vehicle</a>
                    </div>
                    <%
                       List<VehicleDTO> vehicles = (List<VehicleDTO>) request.getAttribute("vehicles");
                       if (vehicles == null || vehicles.isEmpty()) {
                           out.print("<p>No vehicles available.</p>");
                       } else {
                    %>
                    <table class="vehicle-component-table" border="1">
                        <tr>
                            <th>Vehicle ID</th>
                            <th>Vehicle Number</th>
                            <th>Consumption Rate</th>
                            <th>Max Passenger</th>
                            <th>Fuel Type</th>
                            <th>Route ID</th>
                            <th>Capacity</th>
                            <th>Component ID</th>
                            <th>Actions</th>
                        </tr>
                        <%
                            for (VehicleDTO vehicle : vehicles) {
                        %>
                        <tr>
                            <td><%= vehicle.getVehicleId()%></td>
                            <td><%= vehicle.getVehicleNumber()%></td>
                            <td><%= vehicle.getConsumptionRate()%></td>
                            <td><%= vehicle.getMaxPassenger()%></td>
                            <td><%= vehicle.getFuelType()%></td>
                            <td><%= vehicle.getRouteId()%></td>
                            <td><%= vehicle.getCapacity()%></td>
                            <td><%= vehicle.getComponentId()%></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/views/admin/EditVehicle.jsp"
                                   class="btn btn-sm btn-primary">Edit</a>
                                <a href="${pageContext.request.contextPath}/VehicleControl?action=deleteVehicle&vehicleId=<%= vehicle.getVehicleId()%>"
                                   class="btn btn-sm btn-danger" 
                                   onclick="return confirm('Are you sure you want to delete this component?')">Delete</a>
                            </td>
                        </tr>
                        <%}%>
                    </table>
                    <% } %>
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