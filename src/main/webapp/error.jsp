<%-- 
    Document   : error
    Created on : Jul 21, 2025, 11:26:44â¯PM
    Author     : Xiaoli He
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
        <title>Error</title>
    </head>
    <body>
        <h2>${requestScope.errorMsg != null ? requestScope.errorMsg : "An unexpected error occurred."}</h2><br/>
        <a href="javascript:history.back()" class="btn btn-secondary">Back</a>
    </body>
</html>
