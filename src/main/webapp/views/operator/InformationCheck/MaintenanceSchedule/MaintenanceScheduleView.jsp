<%-- 
    Document   : MaintenanceScheduleView
    Created on : Aug 1, 2025, 4:02:52 p.m.
    Author     : shano
--%>

<%@page import="business.MaintenanceScheduleBusinessLogic"%>
<%@page import="java.util.List" %>
<%@page import="model.MaintenanceScheduleDTO" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<MaintenanceScheduleDTO> schedules = (List<MaintenanceScheduleDTO>) request.getAttribute("schedules");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>Transit Fleet Management System - Maintenance Schedule</title>
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Operator - Information Check</h1>
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
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Schedule ID</th>
                                <th>Maintenance ID</th>
                                <th>Schedule Date</th>
                                <th>Note</th>
                                <th>Maintenance Cost</th>
                                <th>Completed</th>

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
                                <td><%= schedule.getCompleted()%></td>
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
