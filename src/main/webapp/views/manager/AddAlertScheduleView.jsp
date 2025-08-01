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
<%MaintenanceAlertDTO cur_alert = (MaintenanceAlertDTO) request.getAttribute("cur_alert");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>Transit Fleet Management System</title>
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Manager - Maintenance Schedule</h1>
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
                    <form action="${pageContext.request.contextPath}/ActiveMaintenanceAlert" method="post">
                        <label for="malert_id">Alert ID</label>
                        <input type="text" id="malert_id" name="malert_id" value="<%= cur_alert.getMaintenanceId() %>" readonly>
                        <label for="selectedDate">Schedule Date:</label>
                        <input type="date" id="selectedDate" name="schedule_date" value="<%= java.time.LocalDate.now().toString() %>">
                        <label for="maint_cost">Maintenance Cost:</label>
                        <input type="text" id="maint_cost" name="maint_cost" >
                        <label for="note">Note:</label>
                        <textarea id="note" name="note" rows="4" cols="50"></textarea>
                        <label for="action"></label>
                        <input type="submit" name="action" class="submit-btn" value="Add Schedule">
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
