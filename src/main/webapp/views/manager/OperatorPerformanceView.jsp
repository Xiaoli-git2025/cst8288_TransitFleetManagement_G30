<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.OperatorPerformanceDTO"%>

<%
    List<OperatorPerformanceDTO> logs = (List<OperatorPerformanceDTO>) request.getAttribute("logs");
%>
<!DOCTYPE html>
<html>
    
<head>
    <meta charset="UTF-8">
    <title>Operator Performance Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layout.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body class="body">
    <div class="header">
            <h1>Operator Performance Report</h1>
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
<!--         <h2>Operator Performance Report</h2>
         <h2>Operator Performance Report</h2> 
         <h2>Operator Performance Report</h2> -->
<br>
<br>
<br>
<br>
         <table border="1" cellpadding="8" cellspacing="0">
              
            <thead>
                <tr>
                    <th>Operator Name</th>
                    <th>Email</th>
                    <th>Log Date</th>
                    <th>Arrive Time (Logged)</th>
                    <th>Depart Time (Logged)</th>
                    <th>Scheduled Arrive Time</th>
                    <th>Scheduled Depart Time</th>
                    <th>On-Time Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    
                    if (logs != null) {
                        for (OperatorPerformanceDTO log : logs) {
                            boolean arriveOnTime = !log.getArriveTime().after(log.getScheduleArriveTime());
                            boolean departOnTime = !log.getDepartTime().after(log.getScheduleDepartTime());
                            String status = (arriveOnTime && departOnTime) ? "On Time" : "Late";
                %>
                <tr>
                    <td><%= log.getUserName() %></td>
                    <td><%= log.getEmail() %></td>
                    <td><%= log.getLogDate() %></td>
                    <td><%= log.getArriveTime() %></td>
                    <td><%= log.getDepartTime() %></td>
                    <td><%= log.getScheduleArriveTime() %></td>
                    <td><%= log.getScheduleDepartTime() %></td>
                    <td><%= status %></td>
                </tr>
                <%      }
                    } else {
                %>
                <tr><td colspan="8">No data available.</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
