<%-- 
    Document   : VehicleComponent
    Created on : Jul 29, 2025, 6:15:09 p.m.
    Author     : Shan Cai
--%>

<%@page import="java.util.List"%>
<%@page import="model.VehicleComponentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <title>Transit Fleet Management System - Vehicle Component</title>
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Component</h1>
            <div class="header-buttons">
                <button class="icon-btn" onclick="history.back()" title="Back">
                    <i class="fas fa-arrow-left"></i>
                </button>
                <a href="${pageContext.request.contextPath}/Login?logout=true" class="icon-btn logout" title="Logout">
                    <a href="EditComponent.jsp"></a>
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
                        <span class="menu-title">Route Management</span>
                        <ul class="submenu">
                            <li><a href="${pageContext.request.contextPath}/Admin?get=route">Route</a></li>
                            <li><a href="${pageContext.request.contextPath}/Admin?get=station">Station</a></li>
                            <li><a href="${pageContext.request.contextPath}/Admin?get=route_schedule">Route Schedule</a></li>
                        </ul>
                    </li>
                    <li class="menu-item">
                        <span class="menu-title">Vehicle Management</span>
                        <ul class="submenu">
                            <li><a href="${pageContext.request.contextPath}/Admin?get=vehicle">Vehicle</a></li>
                            <li><a href="${pageContext.request.contextPath}/Admin?get=component">Vehicle Component</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <div class="container">
                <div class="box_with_menu">
                    <div style="text-align:right; margin-bottom:1rem;">
                        <a href="${pageContext.request.contextPath}/views/admin/AddComponent.jsp"
                           class="btn btn-outline-primary">Add Component</a>
                    </div>
                    <%
                       List<VehicleComponentDTO> vehicleComponents = (List<VehicleComponentDTO>) request.getAttribute("vehicleComponents");
                       if (vehicleComponents == null || vehicleComponents.isEmpty()) {
                           out.print("<p>No components available.</p>");
                       } else {
                    %>
                    <table class="vehicle-component-table" border="1">
                        <tr>
                            <th>Component ID</th>
                            <th>Component Name</th>
                            <th>Vehicle ID</th>
                            <th>Used Hour</th>
                            <th>Threshold Hour</th>
                            <th>Actions</th>
                        </tr>
                        <%
                            for (VehicleComponentDTO vehicleComponent : vehicleComponents) {
                        %>
                        <tr>
                            <td><%= vehicleComponent.getComponentId()%></td>
                            <td><%= vehicleComponent.getComponentName()%></td>
                            <td><%= vehicleComponent.getVehicleId()%></td>
                            <td><%= vehicleComponent.getUsedHour()%></td>
                            <td><%= vehicleComponent.getThresholdHour()%></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/VehicleComponentControl?actions=EditComponent&componentId=<%= vehicleComponent.getComponentId() %>" 
                                   class="btn btn-sm btn-primary">Edit</a>
                                <a href="${pageContext.request.contextPath}/VehicleComponentControl?actions=DeleteComponent&componentId=<%= vehicleComponent.getComponentId()%>"
                                   class="btn btn-sm btn-danger" 
                                   onclick="return confirm('Are you sure you want to delete this component?')">Delete</a>
                            </td>
                        </tr>
                        <%}%>
                    </table>
                    <% } %>
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