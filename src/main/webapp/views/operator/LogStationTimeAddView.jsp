<%-- 
    Document   : OperatorAlertUpdateView
    Created on : Jul 29, 2025, 11:04:52 AM
    Author     : Administrator
--%>
<%@page import="model.*" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    RouteScheduleDTO schedule = (RouteScheduleDTO) request.getAttribute("schedule");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>Transit Fleet Management System - Log Stop Time</title>
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Operator - Log Stop Time</h1>
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
                    <form action="${pageContext.request.contextPath}/LogStopTime" method="post">
                        <label for="schedule_id">Schedule ID:</label>
                        <input type="text" id="schedule_id" name="schedule_id" readonly value="<%= schedule.getScheduleId() %>">
                        <label for="sa_time">Schedule Arrive Time:</label>
                        <input type="time" id="sa_time" name="sa_time" readonly 
                            value="<%= schedule.getScheduleArriveTime().toString().substring(0,5) %>">
                        <label for="sd_time">Schedule Depart Time:</label>
                        <input type="time" id="sd_time" name="sd_time" readonly 
                            value="<%= schedule.getScheduleDepartTime().toString().substring(0,5) %>">
                        <label for="ra_time">Real Arrive Time:</label>
                        <input type="time" id="ra_time" name="ra_time">
                        <label for="rd_time">Real Depart Time:</label>
                        <input type="time" id="rd_time" name="rd_time">
                        <label for="note">Note:</label>
                        <textarea id="note" name="note" rows="4" cols="50"></textarea>
                        <label for="action"></label>
                        <input type="submit" name="action" class="submit-btn" value="Log Stop Time">
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
