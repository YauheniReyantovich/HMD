<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title><spring:message code="home_page"/> </title>

        <link rel="stylesheet" href="../../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../../resources/css/main.css">
        <link rel="stylesheet" href="../../resources/css/fontawesome.css">
    </head>
    <body>

        <span style="float: right">
            <a href="?lang=en">EN</a>
            <a href="?lang=ru">RU</a>
        </span>

        <h3><a href="user"><spring:message code = "add_user"/></a></h3>
    </body>
</html>