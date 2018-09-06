<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="user_information"/> </title>
</head>
<body>

<h2><spring:message code="user_information"/></h2>
<table>
    <tr>
        <td><spring:message code="login"/></td>
        <td>${login}</td>
    </tr>
    <tr>
    <tr>
        <td><spring:message code="password"/></td>
        <td>${password}</td>
    </tr>
    <tr>
        <td><spring:message code="role"/></td>
        <td>${role}</td>
    </tr>
</table>
</body>
</html>