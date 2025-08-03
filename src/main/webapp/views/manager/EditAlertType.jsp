<%@page import="model.AlertDTO"%>
<%@page import="business.AlertTypeBusinessLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*" %>

<%
    String idParam = request.getParameter("id");
    AlertDTO alert = null;
    request.setAttribute("alert", alert);

    try {
        int alertId = Integer.parseInt(idParam);
        AlertTypeBusinessLogic logic = new AlertTypeBusinessLogic();
        alert = logic.getAlertById(alertId); // You need to implement this method
    } catch (Exception e) {
        out.println("<p style='color:red;'>Error loading alert: " + e.getMessage() + "</p>");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Alert Type</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Edit Alert Type</h1>
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
                        <ul class="submenu show">
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
                <div class="box">
                    <form method="post" action="${pageContext.request.contextPath}/EditAlertType">

                        <h2 style="margin-bottom: 1rem; color: #003366;">Edit Alert Type</h2>
                        <input type="hidden" name="alert_id" value="<%= alert.getAlertId()%>" />
                        <label for="alertType">Alert Type</label>
                        <input type="text" id="alertType" name="alert_type" value="<%= alert.getAlertType()%>" required />

                        <label for="alertDescription">Alert Description</label>
                        <input type="text" id="alertDescription" name="alert_description" value="<%= alert.getAlertDescription()%>" required />



                        <div style="margin-top: 1rem; display: flex; gap: 1rem;">
                            <button type="submit" class="submit-btn">Update</button>
                            <a href="${pageContext.request.contextPath}/Manager?get=alert_type" class="submit-btn" style="text-align: center; line-height: 1rem; text-decoration: none;">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p>Contact us at: tfms@algonquinlive.com | Phone: (123) 456-7890 | 
                <a href="linkedin.html" style="color: white; text-decoration: underline;">LinkedIn</a>
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






