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

        <title><spring:message code="user"/> </title>

        <link rel="stylesheet" href="../../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../../resources/css/main.css">
        <link rel="stylesheet" href="../../resources/css/fontawesome.css">
    </head>

    <body>

        <span style="float: right">
            <a href="?lang=en">EN</a>
            <a href="?lang=ru">RU</a>
        </span>

        <h2><spring:message code="enter_user_information"/> </h2>

        <form:form method="post" action="addUser">
            <table>
                <tr>
                    <td class="text-warning"><form:label path="login"><spring:message code="login"/> </form:label></td>
                    <td><form:input path="login" /></td>
                </tr>
                <tr>
                    <td><form:label path="password"><spring:message code="password"/></form:label></td>
                    <td><form:input path="password" /></td>
                </tr>
                <tr>
                    <td><form:label path="role"><spring:message code="role"/></form:label></td>
                    <td><form:input path="role" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="<spring:message code="submit"/>"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
</html>