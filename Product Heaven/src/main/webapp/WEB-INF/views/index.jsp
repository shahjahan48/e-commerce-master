<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Users List</title>
        <link href="<c:url value='/assets/css/bootstrap.css' />" rel="stylesheet"/>
        <link href="<c:url value='/assets/css/app.css' />" rel="stylesheet"/>
    </head>

    <body>
        <div class="generic-container">
            <%@include file="authheader.jsp"%>
            <h4>Welcome User</h4>
        </div>
    </body>
</html>