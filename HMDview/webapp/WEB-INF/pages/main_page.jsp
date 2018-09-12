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

        <title>TITLE</title>

        <link rel="stylesheet" href="../../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../../resources/css/main.css">
        <link rel="stylesheet" href="../../resources/css/fontawesome.css">
    </head>

    <body>

        <div class = "navbar  bg-dark">
            <div class="container">
                <p class="logo">
                    <span class="ital-green">H</span>
                    <span class="ital-white">M</span>
                    <span class="ital-red">D</span>
                </p>
                <a class="right-menu" href="my_profile">Profile</a>
            </div>
        </div>

        <div class="container choose">
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