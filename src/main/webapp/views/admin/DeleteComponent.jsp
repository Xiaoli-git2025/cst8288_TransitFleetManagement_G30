<%-- 
    Document   : DeleteVehicle
    Created on : Jul 29, 2025, 7:32:15 p.m.
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
        <h1>Delete Component</h1>
        <div class="header-buttons">
                <button class="icon-btn" onclick="history.back()" title="Back">
                    <i class="fas fa-arrow-left"></i>
                </button>
                <a href="${pageContext.request.contextPath}/Login?logout=true" class="icon-btn logout" title="Logout">
                    <i class="fas fa-sign-out-alt"></i>
                </a>
            </div>
        <form action="VehicleComponentControl" method="post">
             <input type="hidden" name="action" value="DeleteComponent">
        <table>
            <tr>
                <td>Component ID to Delete:</td>
                <td><input type="number" name="ComponentId" required></td>
            </tr>
        </table>

        <button type="submit">Delete Component</button>
    </form>
    </body>
    
</html>
