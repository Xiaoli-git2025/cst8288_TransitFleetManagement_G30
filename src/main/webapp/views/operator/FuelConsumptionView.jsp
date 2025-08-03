<%-- 
    Document   : OperatorAlertView
    Created on : Jul 28, 2025, 9:38:06 PM
    Author     : Administrator
--%>
<%@page import="model.*" %>
<%@page import="dao.*" %>
<%@page import="business.FuelConsumptionBusinessLogic" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<VehicleDTO> vehicles = (List<VehicleDTO>) request.getAttribute("vehicles");
    //int vehicle_id = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>Transit Fleet Management System - Log Fuel Consumption</title>
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Operator - Log Fuel Consumption</h1>
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
                        <span class="menu-title">Record Status</span>
                        <ul class="submenu">
                            <li><a href="${pageContext.request.contextPath}/Operator?get=stop_time">Log Stop Time</a></li>
                            <li><a href="${pageContext.request.contextPath}/Operator?get=fuel_consumption">Log Fuel Consumption</a></li>
                            <li><a href="${pageContext.request.contextPath}/Operator?get=operator_alert">Generate Alert</a></li>
                        </ul>
                    </li>
                    <li class="menu-item">
                        <span class="menu-title">Information Check</span>
                        <ul class="submenu">
                            <li><a href="${pageContext.request.contextPath}/Operator?get=vehicle_alert">Vehicle Alert</a></li>
                            <li><a href="${pageContext.request.contextPath}/Operator?get=maintenance_schedule">Maintenance Schedule</a></li>
                            <li><a href="${pageContext.request.contextPath}/Operator?get=operator_performance">Individual Performance</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>

            <!-- Main container -->
            <div class="container">
                <div class="box_with_menu">
                    <form method="post" action="${pageContext.request.contextPath}/FuelConsumption">
                        <div style="display: flex; align-items: center; gap: 10px;">
                            <label for="vehicle_id">Select Vehicle</label>
                            <select id="vehicle_id" name="vehicle_id">
                                 <% for (VehicleDTO vehicle : vehicles) {
                                 %>
                                    <option value="<%= vehicle.getVehicleId()%>"><%= vehicle.getVehicleNumber()%></option>
                                 <% } %>
                            </select>
                            <input type="submit" name="action" class="submit-btn" value="Load Fuel Consumption" />
                        </div>

                        <%
                            //Integer vehicleIdObj = (Integer) request.getSession().getAttribute("vehicle_id");
                            //int vehicleid = (vehicleIdObj != null) ? vehicleIdObj : -1;
                            List<FuelConsumptionDTO> fuels = (List<FuelConsumptionDTO>) request.getAttribute("fuel_consumptions");
                            if (fuels != null && !fuels.isEmpty()) {
                                FuelConsumptionBusinessLogic logic = new FuelConsumptionBusinessLogic();
                        %>

                        <table border="1">
                            <thead>
                                <tr>
                                    <th>FC Id</th>
                                    <th>Vehicle Number</th>
                                    <th>Date</th>
                                    <th>Miles Traveled</th>
                                    <th>Unit Price</th>
                                    <th style="width:200px;">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (FuelConsumptionDTO fuel : fuels) {
                                    int fc_id = fuel.getFcId();
                                %>
                                <tr>
                                    <td><%= fc_id %></td>
                                    <td><%= logic.getVehicleByFCId(fc_id).getVehicleNumber() %></td>
                                    <td><%= fuel.getDate() %></td>
                                    <td><%= fuel.getMilesTraveled() %></td>
                                    <td><%= fuel.getUnitPrice() %></td>
                                    <td>
                                        <a href="<%= request.getContextPath()%>/FuelConsumption?fc_id=<%= fc_id %>&get=log_update"
                                           class="btn btn-sm btn-primary">Edit</a>
                                        <a href="<%= request.getContextPath()%>/FuelConsumption?fc_id=<%= fc_id %>&get=log_delete"
                                           class="btn btn-sm btn-primary" onclick="return confirm('Are you sure you want to delete this record?');">Delete</a>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                        <%
                            }
                        %>
                        <input type="submit" name="action" class="submit-btn" value="Log New Fuel Consumption" />  
                    </form>
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
