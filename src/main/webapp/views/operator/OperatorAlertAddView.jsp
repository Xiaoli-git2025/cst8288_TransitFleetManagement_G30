<%-- 
    Document   : OperatorAlertUpdateView
    Created on : Jul 29, 2025, 11:04:52 AM
    Author     : Administrator
--%>
<%@page import="model.MaintenanceAlertDTO" %>
<%@page import="model.VehicleComponentDTO" %>
<%@page import="model.AlertDTO" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<VehicleComponentDTO> components = (List<VehicleComponentDTO>) request.getAttribute("components");
    List<AlertDTO> alert_types = (List<AlertDTO>) request.getAttribute("alert_types");
    String cur_vehicle = (String)request.getAttribute("cur_vehicle");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>Transit Fleet Management System - Maintenance Alert Update</title>
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
                            <li><a href="${pageContext.request.contextPath}/Operator?get=maintenance_schedule">Maintenance Schedule</a></li>
                            <li><a href="${pageContext.request.contextPath}/Operator?get=operator_performance">Individual Performance</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>

            <!-- Main container -->
            <div class="container">
                <div class="box_with_menu">
                    <form action="${pageContext.request.contextPath}/MaintenanceAlert" method="post">
                        <label>Vehicle: <%= cur_vehicle %></label>
                        <label for="component_id">Component</label>
                        <select name="component_id">
                            <% for (VehicleComponentDTO component : components) {%>
                                <option value="<%= component.getComponentId()%>"><%= component.getComponentName()%></option>
                            <% }%>
                        </select>
                        <label for="alert_type">ALert Type</label>
                        <select name="alert_type">
                            <% for (AlertDTO alert_type : alert_types) {%>
                                <option value="<%= alert_type.getAlertId()%>"><%= alert_type.getAlertType()%></option>
                            <% }%>
                        </select>
                        <label for="selectedDate">Alert Date:</label>
                        <input type="date" id="selectedDate" name="alert_date" value="<%= java.time.LocalDate.now().toString() %>"
                        
                        <label for="action"></label>
                        <input type="submit" name="action" class="submit-btn" value="Add Alert">
                    </form>
                </div>
            </div>
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
