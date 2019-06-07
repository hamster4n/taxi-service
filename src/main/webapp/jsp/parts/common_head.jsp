<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" scope="session"/>
<html lang="${sessionScope.lang}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/daterangepicker.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/map.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/mapbox-gl.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/mapbox-gl-directions.css" rel="stylesheet" type="text/css">

    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/mapbox-gl.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/mapbox-gl-directions.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/calendar.js"></script>
</head>
<body>

<c:set var="role" scope="session" value="${role}"/>
<c:if test="${role eq null}">
    <%@include file="navbar_default.jsp" %>
</c:if>
<c:if test="${role eq 'USER'}">
    <%@include file="navbar_user.jsp" %>
</c:if>
<c:if test="${role eq 'ADMIN'}">
    <%@include file="navbar_admin.jsp" %>
</c:if>
</body>
</html>