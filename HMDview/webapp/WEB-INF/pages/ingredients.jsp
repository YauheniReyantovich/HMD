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
        function submitNewCategory() {
            $('#newCategoryForm').submit();
        }
        function submitNewLayer() {
            $('#newLayerForm').submit();
        }
    </script>

        <%@include file="layouts/high_menu_bar.jsp"%>

        <div class="container margin10px">
            <form:form method="post" modelAttribute="categoryForm" class="form-inline" action="addCategory" id="newCategoryForm">
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
                        <td onClick="submitNewCategory()" class="centered btn-primary mb-2 hovered">
                            <spring:message code="ingredients.categories.newCategory"/>
                        </td>
                        <td>
                            <label for="inputEngCat" class="sr-only">Category</label>
                            <form:input type="text" path="engName" class="likeCell" AUTOCOMPLETE="off" id="inputEngCat" placeholder="Category"/>
                        </td>
                        <td>
                            <label for="inputRusCat" class="sr-only">Категория</label>
                            <form:input type="text" path="rusName" class="likeCell" AUTOCOMPLETE="off" id="inputRusCat" placeholder="Категория"/>
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

    <script type="text/javascript">
        function showEveryIngredientChanceInLayer(){
            var i;
            var maxIngredients = document.getElementById("inputMaxIngredients").value;
            var oldMaxIngredients = document.getElementById("ingredientChances").children.length;
            if(maxIngredients > oldMaxIngredients) {
                var newInput;
                for (i = 0; i < maxIngredients - oldMaxIngredients; i++) {
                    newInput = document.createElement("input");
                    newInput.name = "layerInput";
                    newInput.type = "number";
                    newInput.step = 1;
                    newInput.min = 0;
                    newInput.classList.add("likeCell");

                    document.getElementById("ingredientChances").appendChild(newInput);
                }
            }
            if(maxIngredients < oldMaxIngredients) {
                for (i = 0; i < oldMaxIngredients - maxIngredients; i++) {
                    document.getElementById("ingredientChances").removeChild(document.getElementById("ingredientChances").firstChild)
                }
            }
            for(i = 0; i < maxIngredients; i++){
                if(document.getElementById("ingredientChances").children[i].nodeName === "INPUT") {
                    document.getElementById("ingredientChances").children[i].value = Math.floor((100 * (maxIngredients - i) / maxIngredients));
                }
            }
        }
    </script>

    <div class="container margin10px">
        <form:form method="post" modelAttribute="layerForm" class="form-inline" action="addLayer"  id="newLayerForm">
            <table class="demotable normal">
                <thead>
                <tr class="centered">
                    <td>№</td>
                    <td><spring:message code="ingredients.layers.engColumn"/> </td>
                    <td><spring:message code="ingredients.layers.rusColumn"/> </td>
                    <td><spring:message code="ingredients.layers.maxIngredients"/></td>
                    <td><spring:message code="ingredients.layers.IngredientChance"/></td>
                </tr>
                <tbody>
                <c:forEach items="${allLayers}" var="layer">
                    <tr>
                        <td>${layer.engName}</td>
                        <td>${layer.rusName}</td>
                        <td>${layer.maxIngredients}</td>
                        <td>
                            <c:forEach items="${layer.ingredientChance}" var="chance">
                                <span>${chance}&nbsp &nbsp</span>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
                <tfoot>
                <tr>
                    <td onClick="submitNewLayer()" class="centered btn-primary mb-2 hovered">
                        <spring:message code="ingredients.layers.newLayer"/>
                    </td>
                    <td>
                        <label for="inputEngLayer" class="sr-only">Layer</label>
                        <form:input type="text" path="engName" class="likeCell" AUTOCOMPLETE="off" id="inputEngLayer" placeholder="Layer"/>
                    </td>
                    <td>
                        <label for="inputRusLayer" class="sr-only">Слой</label>
                        <form:input type="text" path="rusName" class="likeCell" AUTOCOMPLETE="off" id="inputRusLayer" placeholder="Слой"/>
                    </td>
                    <td>
                        <label for="inputMaxIngredients" class="sr-only">Layer</label>
                        <form:input type="number" path="maxIngredients" class="likeCell" id="inputMaxIngredients" onchange="showEveryIngredientChanceInLayer()"/>
                    </td>
                    <td id="ingredientChances">

                    </td>
                </tr>
            </table>
        </form:form>
    </div>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="../../resources/js/bootstrap.min.js"></script>
        <script src="../../resources/js/main.js"></script>
    </body>
</html>