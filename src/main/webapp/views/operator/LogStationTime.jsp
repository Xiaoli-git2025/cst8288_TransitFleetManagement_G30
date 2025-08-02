<%-- 
    Document   : OperatorAlertView
    Created on : Jul 28, 2025, 9:38:06 PM
    Author     : Administrator
--%>
<%@page import="model.*" %>
<%@page import="business.*" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    StopTimeBusinessLogic logic = new StopTimeBusinessLogic();
    int user_id = (Integer)request.getSession().getAttribute("user_id");
    RouteDTO route = logic.getRouteByUserId(user_id);
    List<StationDTO> stations = logic.getStationsbyRouteId(route.getRouteId());
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
                    <form method="post" action="${pageContext.request.contextPath}/LogStopTime">
                        <div style="display: flex; align-items: center; gap: 10px;">
                            <label for="station_id">Select Station</label>
                            <select id="station_id" name="station_id">
                                 <% for (StationDTO station : stations) {
                                 %>
                                    <option value="<%= station.getStationId() %>"><%= station.getStationName()%></option>
                                 <% } %>
                            </select>
                            <!--<label for="schedule_number">Select Schedule Number</label>
                            <select id="schedule_number" name="schedule_number">
                                 <% for (int i=1; i<24; i++) {
                                 %>
                                    <option value="<%= i %>"><%=i%></option>
                                 <% } %>
                            </select>-->
                            <input type="submit" name="action" class="submit-btn" value="Load Route Schedule" />
                        </div>

                        <%
                            List<RouteScheduleDTO> schedules = (List<RouteScheduleDTO>) request.getAttribute("route_schedules");
                            if (schedules != null && !schedules.isEmpty()) {
                                //AlertBusinessLogic logic = new AlertBusinessLogic();
                        %>

                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Schedule Id</th>
                                    <th>Route Number</th>
                                    <th>Station Name </th>
                                    <th>Schedule Number</th>
                                    <th>Schedule arrive time</th>
                                    <th>Schedule depart time</th>
                                    <th style="width:200px;">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (RouteScheduleDTO schedule : schedules) {
                                    int schedule_id = schedule.getScheduleId();
                                %>
                                <tr>
                                    <td><%= schedule_id %></td>
                                    <td><%= logic.getRouteByScheduleId(schedule_id).getRouteNumber() %></td>
                                    <td><%= logic.getStationByScheduleId(schedule_id).getStationName() %></td>
                                    <td><%= schedule.getScheduleNumber() %></td>
                                    <td><%= schedule.getScheduleArriveTime()%></td>
                                    <td><%= schedule.getScheduleDepartTime()%></td>
                                    <td>
                                        <a href="<%= request.getContextPath()%>/LogStopTime?schedule_id=<%= schedule_id %>&get=log_new"
                                           class="btn btn-sm btn-primary">Log Time</a>
                                        <a href="<%= request.getContextPath()%>/LogStopTime?schedule_id=<%= schedule_id %>&get=log_update"
                                           class="btn btn-sm btn-primary">Edit</a>
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
