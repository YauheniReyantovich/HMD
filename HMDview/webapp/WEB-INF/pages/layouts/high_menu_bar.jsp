<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
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

        <div class = "navbar bg-dark">
            <div class="container">
                <p class="logo">
                    <span class="ital-green">H</span>
                    <span class="ital-white">M</span>
                    <span class="ital-red">D</span>
                </p>
                <div class="btn-group">
                    <button type="button" class="navbar-toggler" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-angle-double-down"></i>
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="edit_recommendation">Edit rec</a>
                        <a class="dropdown-item" href="recommendation">Rec</a>
                        <a class="dropdown-item" href="my_profile">Profile</a>
                        <a class="dropdown-item" href="ingredients">Ingredients</a>
                        <div class="dropdown-divider"></div>
                        <form id="logoutForm" method="POST" action="${contextPath}/logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                        <a class="dropdown-item" href="#" onclick="document.forms['logoutForm'].submit()">Logout</a>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="../../resources/js/bootstrap.min.js"></script>
    </body>