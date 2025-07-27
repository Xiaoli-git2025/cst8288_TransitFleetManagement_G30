<%-- 
    Document   : register
    Created on : Jun 28, 2025, 5:47:04 PM
    Author     : Administrator
--%>
<%@page import="java.util.List" %>
<%@page import="model.RouteDTO" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RouteDTO> routes = (List<RouteDTO>) request.getAttribute("routes");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Transit Fleet Management System - Register</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>
    <body>
        <!--Header with navigation-->
        <div class="header">
            <h1>Transit Fleet Management System - Register</h1>
            <div class="header-buttons">
                <button class="icon-btn" onclick="history.back()" title="Back">
                    <i class="fas fa-arrow-left"></i>
                </button>
                <a href="${pageContext.request.contextPath}/Login?logout=true" class="icon-btn logout" title="Logout">
                    <i class="fas fa-sign-out-alt"></i>
                </a>
            </div>
        </div>
        <!--Main container-->
        <div class="main">
            <section class="content">
                <div class="box">
                    <div class="submit-form">

                    <form action="${pageContext.request.contextPath}/Login" method="post">
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name" required>
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required>
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" required>
                        <label for="role">Role</label>
                        <select name="role">
                            <option value="manager">Manager</option>
                            <option value="operator">Operator</option>
                        </select>
                        <label for="route_id">Route Assigned</label>
                        
                        <select name="route_id">
                            <% for (RouteDTO route : routes) {%>
                                <option value="<%= route.getRouteId()%>"><%= route.getRouteNumber()%></option>
                            <% }%>
                        </select><br/><br/>
                        <input type="submit" name="action" class="submit-btn" value="Register Me">
                    </form>

                    </div>
                </div>
            </section>
        </div>
        <!-- Footer -->
        <div class="footer">
            <p>Contact us at: tfms@algonquinlive.com | Phone: (123) 456-7890 | 
                <a href="linkedin.html">LinkedIn</a>
            </p>
        </div>
    </body>
</html>
