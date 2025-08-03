<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.MaintenanceAlertDTO"%>
<%
    List<MaintenanceAlertDTO> costs = (List<MaintenanceAlertDTO>) request.getAttribute("costs");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maintenance Cost Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body class="body">
    <div class="header">
        <h1>Maintenance Cost Report</h1>
        <div class="header-buttons">
            <button class="icon-btn" onclick="history.back()" title="Back">
                <i class="fas fa-arrow-left"></i>
            </button>
            <a href="${pageContext.request.contextPath}/Login?logout=true" class="icon-btn logout" title="Logout">
                <i class="fas fa-sign-out-alt"></i>
            </a>
        </div>
    </div>

    <div class="container">
        <br><br><br><br>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>
                    <th>Vehicle ID</th>
                    <th>Vehicle Number</th>
                    <th>Component Name</th>
                    <th>Schedule Date</th>
                    <th>Note</th>
                    <th>Maintenance Cost</th>
                </tr>
            </thead>
            <tbody>
                <% if (costs != null) {
                    for (MaintenanceAlertDTO dto : costs) { %>
                        <tr>
                            <td><%= dto.getVehicleId() %></td>
                            <td><%= dto.getVehicleNumber() %></td>
                            <td><%= dto.getComponentName() %></td>
                            <td><%= dto.getScheduleDate() %></td>
                            <td><%= dto.getNote() %></td>
                            <td><%= dto.getMaintenanceCost() %></td>
                        </tr>
                <%  }
                   } else { %>
                    <tr><td colspan="6">No data available.</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
