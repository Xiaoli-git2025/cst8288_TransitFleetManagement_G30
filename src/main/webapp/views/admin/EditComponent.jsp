<%-- 
    Document   : EditVehicle
    Created on : Jul 29, 2025, 7:31:41 p.m.
    Author     : shano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JTransit Fleet Management System - Vehicle</title>
    </head>
    <body>
        <h1>Edit Component</h1>
        <div class="header-buttons">
                <button class="icon-btn" onclick="history.back()" title="Back">
                    <i class="fas fa-arrow-left"></i>
                </button>
                <a href="${pageContext.request.contextPath}/Login?logout=true" class="icon-btn logout" title="Logout">
                    <i class="fas fa-sign-out-alt"></i>
                </a>
            </div>
        <form action ="${pageContext.request.contextPath}/VehicleComponentControl" method="post">
            <input type="hidden" name="action" value="EditComponent">
        <table>
            <tr>
                <td>Component ID:</td>
                <td><input type="number" name="Component_id" required></td>
            </tr>
            <tr>
                <td>Component Name:</td>
                <td><input type="text" name="Component_name" required></td>
            </tr>
            <tr>
                <td>Vehicle ID:</td>
                <td><input type="number" name="Vehicle_id" required></td>
                <tr>
                <td>Used Hour:</td>
                <td><input type="number" name="used_hour" required></td>
            </tr>
            <tr>
                <td>Threshold Hour:</td>
                <td><input type="number" name="threshold_hour" required></td>
            </tr>
            </table>
            <button type="submit">Edit Component</button>
    </body>
</html>
