<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="home_page"/> </title>
</head>
<body>

<span style="float: right">
    <a href="?lang=en">EN</a>
    <a href="?lang=ru">RU</a>
</span>

<h3><a href="user"><spring:message code = "add_user"/></a></h3>
</body>
</html>