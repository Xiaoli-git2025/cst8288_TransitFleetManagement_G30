<%-- 
    Document   : OperatorAlertView
    Created on : Jul 28, 2025, 9:38:06 PM
    Author     : Administrator
--%>
<%@page import="model.MaintenanceAlertDTO" %>
<%@page import="model.VehicleComponentDTO" %>
<%@page import="model.VehicleDTO" %>
<%@page import="model.AlertDTO" %>
<%@page import="business.AlertBusinessLogic" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<VehicleDTO> vehicles = (List<VehicleDTO>) request.getAttribute("vehicles");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>Transit Fleet Management System - Maintenance Alert</title>
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Operator - Log Alert</h1>
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
                            <li><a href="${pageContext.request.contextPath}/Operator?get=maintance_schedule">Maintenance Schedule</a></li>
                            <li><a href="${pageContext.request.contextPath}/Operator?get=operator_performance">Individual Performance</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>

            <!-- Main container -->
            <div class="container">
                <div class="box_with_menu">
                    <form method="post" action="${pageContext.request.contextPath}/MaintenanceAlert">
                        
                        <label for="vehicle_id">Select Vehicle</label>
                        <div style="display: flex; align-items: center; gap: 10px;">
                        <%
                        int selectedVehicleId = (request.getAttribute("cur_vehicle") != null)
                            ? (Integer) request.getAttribute("cur_vehicle")
                            : 0;
                        %>
                        
                        <select id="vehicleSelect" name="vehicle_id">
                             <% for (VehicleDTO vehicle : vehicles) {
                                int id = vehicle.getVehicleId();
                                String selected = (id == selectedVehicleId) ? "selected" : "";
                             %>
                                <option value="<%= id %>" <%= selected %>><%= vehicle.getVehicleNumber() %></option>
                             <% } %>
                        </select>
                        
                        <input type="submit" name="action" class="submit-btn" value="Load" />
                        </div>

                        <%
                            List<MaintenanceAlertDTO> alerts = (List<MaintenanceAlertDTO>) request.getAttribute("maint_alerts");
                            if (alerts != null && !alerts.isEmpty()) {
                                AlertBusinessLogic logic = new AlertBusinessLogic();
                        %>

                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Maintenance Alert Id</th>
                                    <th>Alert Type</th>
                                    <th>Component</th>
                                    <th>Alert Date</th>
                                    <th>Resolved</th>
                                    <th style="width:200px;">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (MaintenanceAlertDTO alert : alerts) {%>
                                <tr>
                                    <td><%= alert.getMaintenanceId()%></td>
                                    <td><%= logic.getAlertType(alert.getAlertId())%></td>
                                    <td><%= logic.getComponentNameByComponentId(alert.getComponentId())%></td>
                                    <td><%= alert.getAlertDate()%></td>
                                    <td><input type="checkbox" disabled <%= alert.isResolved() ? "checked" : "" %> /></td>
                                    <td>
                                        <a href="<%= request.getContextPath()%>/MaintenanceAlert?alert_id=<%= alert.getMaintenanceId()%>&get=update_alert"
                                           class="btn btn-sm btn-primary">Edit</a>
                                        <a href="<%= request.getContextPath()%>/MaintenanceAlert?alert_id=<%= alert.getMaintenanceId()%>&get=delete_alert"
                                           class="btn btn-sm btn-primary" onclick="return confirm('Are you sure you want to delete this alert?');">Delete</a>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                        <%
                            }
                        %>
                        
                        <input type="submit" name="action" class="submit-btn" value="Report New Alert" />                      
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
                const select = document.getElementById("vehicleSelect");
                const link = document.getElementById("reportLink");

                // Update the link when selection changes
                select.addEventListener("change", function () {
                    const selectedId = select.value;
                    link.href = `${link.dataset.base}?get=new_alert&vehicle_id=${selectedId}`;
                });

                // Optional: update immediately on page load if there's a selected value
                const initialId = select.value;
                link.href = `${link.dataset.base}?get=new_alert&vehicle_id=${initialId}`;
            });
        </script>
    </body>
</html>
