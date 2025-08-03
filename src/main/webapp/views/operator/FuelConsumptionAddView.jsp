<%-- 
    Document   : OperatorAlertUpdateView
    Created on : Jul 29, 2025, 11:04:52 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Integer cur_vehicle_id = (Integer)request.getSession().getAttribute("vehicle_id");
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
                    <form action="${pageContext.request.contextPath}/FuelConsumption" method="post">
                        <label for="vehicle_id">Vehicle Id:</label>
                        <input type="text" name="vehicle_id" id="vehicle_id" readonly value="<%= cur_vehicle_id %>">
                        <label for="miles_traveled">Miles Traveled:</label>
                        <input type="text" name="miles_traveled" id="miles_traveled">
                        <label for="unit_price">Unit Price:</label>
                        <input type="text" name="unit_price" id="unit_price">
                        <label for="action"></label>
                        <input type="submit" name="action" class="submit-btn" value="Log Fuel Consumption">
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
