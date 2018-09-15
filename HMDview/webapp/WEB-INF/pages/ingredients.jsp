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

        <title>Ingredients</title>

        <link rel="stylesheet" href="../../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../../resources/css/fontawesome.css">
        <link rel="stylesheet" href="../../resources/css/main.css">

    </head>

    <body>

    <script type="text/javascript">
        function submitForm() {

            $('#crudForm').submit();

        }
    </script>

        <%@include file="layouts/high_menu_bar.jsp"%>

        <div class="container margin10px">
            <form:form method="post" modelAttribute="categoryForm" class="form-inline" action="addCategory" id="crudForm">
                <table class="demotable">
                    <thead>
                    <tr class="centered">
                        <td>№</td>
                        <td><spring:message code="ingredients.categories.engColumn"/> </td>
                        <td><spring:message code="ingredients.categories.rusColumn"/> </td>
                    </tr>
                    <tbody>
                    <c:forEach items="${allCategories}" var="category">
                        <tr>
                            <td>${category.engName}</td>
                            <td>${category.rusName}</td>
                        </tr>
                    </c:forEach>
                    <tfoot>
                    <tr>
                        <td onClick="submitForm()" class="centered btn-primary mb-2 hovered">
                            <spring:message code="ingredients.categories.newCategory"/>
                        </td>
                        <td>
                            <label for="inputEngCat" class="sr-only">Category</label>
                            <form:input type="text" path="engName" class="form-control width100" id="inputEngCat" placeholder="Category"></form:input>
                        </td>
                        <td>
                            <label for="inputRusCat" class="sr-only">Категория</label>
                            <form:input type="text" path="rusName" class="form-control width100" id="inputRusCat" placeholder="Категория"></form:input>
                        </td>
                    </tr>

                </table>
                <%--<label for="inputState"></label>--%>
                <%--<select id="inputState" class="form-control">--%>
                    <%--<option selected>New</option>--%>
                    <%--<c:forEach items="${allCategories}" var="category">--%>
                        <%--<option>${category.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
                <%--<div class="form-group mx-sm-3 mb-2">--%>
                    <%--<label for="inputRusCat" class="sr-only">Категория</label>--%>
                    <%--<input type="text" class="form-control" id="inputRusCat" placeholder="Категория">--%>
                <%--</div>--%>
                <%--<div class="form-group mx-sm-3 mb-2">--%>
                    <%--<label for="inputEngCat" class="sr-only">Category</label>--%>
                    <%--<input type="text" class="form-control" id="inputEngCat" placeholder="Category">--%>
                <%--</div>--%>

                <%--<button id="catButton" type="submit" class="btn btn-primary mb-2">New category</button>--%>

            </form:form>
        </div>




        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="../../resources/js/bootstrap.min.js"></script>
        <script src="../../resources/js/main.js"></script>
    </body>
</html>