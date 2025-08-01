<%-- 
    Document   : EditVehicle
    Created on : Jul 31, 2025, 3:36:42 p.m.
    Author     : shan cai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JTransit Fleet Management System - Vehicle</title>
    </head>
    <body class="body">
        <!-- Header -->
        <div class="header">
            <h1>Vehicle-Update</h1>
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
        <div class="box">
        <form action ="${pageContext.request.contextPath}/VehicleControl" method="post">
        <table>
            <tr>
                <td>Vehicle ID:</td>
                <td><input type ="number" name ="vehicle_id" required></td>
            </tr>
                <tr>
                    <td>Vehicle Number:</td>
                    <td><input type="text" name="vehicle_number" required></td>
                </tr>
                <tr>
                    <td>Consumption Rate:</td>
                    <td><input type="number" name="consumption_rate" required></td>
                </tr>
                <tr>
                    <td>Max Passenger:</td>
                    <td><input type="number" name="max_passenger" required></td>
                </tr>
                <tr>
                    <td>Fuel Type:</td>
                    <td><input type="text" name="fuel_type" required></td> 
                </tr>
                </tr>
                <tr>
                    <td>Route ID:</td>
                    <td><input type="number" name="route_id" required></td>
                </tr>
                </tr>
                <tr>
                    <td>Capacity:</td>
                    <td><input type="number" name="capacity" required></td>
                </tr>
                </tr>
            </table>
            <button type="submit" name="action" value="UpdateVehicle" class="btn btn-sm btn-primary">Edit Vehicle</button>
        </form>
        </div>
        </div>
    </body>
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
</html>