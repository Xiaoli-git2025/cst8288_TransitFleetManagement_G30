<%-- 
    Document   : AdminDashBoard
    Created on : Jul 23, 2025, 3:08:46 PM
    Author     : Xiaoli He
--%>
<%@page import="business.AlertTypeBusinessLogic"%>
<%@page import="java.util.List" %>
<%@page import="model.AlertDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<AlertDTO> alerts = null;
    try {
        AlertTypeBusinessLogic alertLogic = new AlertTypeBusinessLogic();
        alerts = alertLogic.getAllAlert();
    } catch (Exception e) {
        out.println("<p style='color:red;'>Error loading alerts: " + e.getMessage() + "</p>");
        alerts = new java.util.ArrayList<>();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>Transit Fleet Management System - Manager</title>
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Manager Dashboard</h1>
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
                <!-- Add your page-specific content here -->
                <div class="box_with_menu">
                    <div style="text-align:right; margin-bottom:1rem;">
                        <a href="${pageContext.request.contextPath}/AddAlertType"
                           class="btn btn-outline-primary">Add Alert Type</a>

                    </div>

                    <table border="1">
                        <thead>
                            <tr>
                                <th>Alert ID</th>
                                <th>Alert Type</th>
                                <th>Description</th>
                                <th style="width:200px;">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (alerts != null && !alerts.isEmpty()) {
                                    for (AlertDTO alert : alerts) {%>
                            <tr>
                                <td><%= alert.getAlertId()%></td>
                                <td><%= alert.getAlertType()%></td>
                                <td><%= alert.getAlertDescription()%></td>
                                <td>
                                    <a href="<%= request.getContextPath()%>/EditAlertType?id=<%= alert.getAlertId()%>" class="btn btn-sm btn-primary">Edit</a>

                                    <a href="<%= request.getContextPath()%>/DeleteAlertType?id=<%= alert.getAlertId()%>" 
                                       class="btn btn-sm btn-danger" 
                                       onclick="return confirm('Are you sure you want to delete this alert type?');">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                            <%     }
                            } else { %>
                            <tr><td colspan="4">No alert types found.</td></tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
                <!-- all cost report -->
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
