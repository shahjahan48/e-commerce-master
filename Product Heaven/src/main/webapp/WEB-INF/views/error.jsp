<%-- Created by User: shahj, Date: 24-Jan-20, Time: 9:19 PM --%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${title}</title>
</head>
<body>
    <div class="content-area">
        <div class="container-fluid">
            <p>This is <strong>${title}</strong> page</p>
        </div>
    </div>
</body>

</html>
