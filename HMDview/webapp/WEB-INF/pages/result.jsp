<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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

        <title><spring:message code="user_information"/> </title>

        <link rel="stylesheet" href="../../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../../resources/css/main.css">
        <link rel="stylesheet" href="../../resources/css/fontawesome.css">
    </head>
    <body>

        <h2><spring:message code="user_information"/></h2>
        <table>
            <tr>
                <td><spring:message code="login"/></td>
                <td>${login}</td>
            </tr>
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