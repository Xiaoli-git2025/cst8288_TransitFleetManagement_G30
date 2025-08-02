<%-- 
    Document   : OperatorAlertView
    Created on : Jul 28, 2025, 9:38:06 PM
    Author     : Administrator
--%>
<%@page import="model.MaintenanceAlertDTO" %>
<%@page import="model.MaintenanceScheduleDTO" %>
<%@page import="model.VehicleComponentDTO" %>
<%@page import="model.VehicleDTO" %>
<%@page import="model.AlertDTO" %>
<%@page import="business.AlertBusinessLogic" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                        <span class="menu-title">Alert Management</span>
                        <ul class="submenu">
                            <li><a href="${pageContext.request.contextPath}/Manager?get=alert_type">Alert Type</a></li>
                            <li><a href="${pageContext.request.contextPath}/Manager?get=all_alerts">Check Alert</a></li>
                            <li><a href="${pageContext.request.contextPath}/Manager?get=maintenance_schedule">Maintenance Schedule</a></li>
                        </ul>
                    </li>
                    <li class="menu-item">
                        <span class="menu-title">Generate Reports</span>
                        <ul class="submenu">
                            <li><a href="${pageContext.request.contextPath}/Manager?get=operator_performance">Operator Performance</a></li>
                            <li><a href="${pageContext.request.contextPath}/Manager?get=maintenance_report">Maintenance Report</a></li>
                            <li><a href="${pageContext.request.contextPath}/Manager?get=maintenance_cost">Maintenance Expenses</a></li>
                            <li><a href="${pageContext.request.contextPath}/Manager?get=fuel_energy_cost">Fuel/Energy Cost</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>

            <!-- Main container -->
            <div class="container">
                <div class="box_with_menu">
                    <form method="post" action="${pageContext.request.contextPath}/ActiveMaintenanceAlert">
                        <%
                            List<MaintenanceScheduleDTO> schedules = (List<MaintenanceScheduleDTO>) request.getAttribute("schedules");
                            if (schedules != null && !schedules.isEmpty()) {
                                AlertBusinessLogic logic = new AlertBusinessLogic();
                        %>

                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Maintenance Schedule Id</th>
                                    <th>Maintenance Alert Id</th>
                                    <th>Schedule Date</th>
                                    <th>Note</th>
                                    <th>Maintenance Cost</th>
                                    <th>Completed</th>
                                    <th style="width:200px;">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (MaintenanceScheduleDTO schedule : schedules) {%>
                                <tr>
                                    <td><%= schedule.getScheduleId()%></td>
                                    <td><%= schedule.getMaintenanceId()%></td>
                                    <td><%= schedule.getScheduleDate()%></td>
                                    <td><%= schedule.getNote()%></td>
                                    <td><%= schedule.getMaintenanceCost()%></td>
                                    <td><input type="checkbox" disabled <%= schedule.isCompleted()? "checked" : "" %> /></td>
                                    <td>
                                        <a href="<%= request.getContextPath()%>/ActiveMaintenanceAlert?schedule_id=<%= schedule.getScheduleId()%>&get=update_schedule"
                                           class="btn btn-sm btn-primary">Update Schedule</a>
                                        <a href="<%= request.getContextPath()%>/ActiveMaintenanceAlert?schedule_id=<%= schedule.getScheduleId()%>&get=delete_schedule"
                                           class="btn btn-sm btn-primary" onclick="return confirm('Are you sure you want to delete this schedule?');">Delete</a>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                        <%
                            }
                        %>                    
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
