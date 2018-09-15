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

        <title>TITLE</title>

        <link rel="stylesheet" href="../../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../../resources/css/main.css">
        <link rel="stylesheet" href="../../resources/css/fontawesome.css">
    </head>

    <body>

        <%@include file="layouts/high_menu_bar.jsp"%>

        <div class="container margin10px">
            <div class="row centered high">
                <a class="col-lg-4 making block" href="create_pizza">
                    <h6 class="vertical-middle">Сделай сам</h6>
                </a>
                <a class="col-lg-4 random block" href="pizza_view">
                    <h6 class="vertical-middle">Создать случайно</h6>
                </a>
                <a class="col-lg-4 dayBest block" href="pizza_view">
                    <h6 class="vertical-middle">Пицца дня</h6>
                </a>
            </div>
        </div>
    </body>
</html>