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

        <title>Create pizza</title>

        <link rel="stylesheet" href="../../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../../resources/css/main.css">
        <link rel="stylesheet" href="../../resources/css/fontawesome.css">
    </head>

    <body>

        <%@include file="layouts/high_menu_bar.jsp"%>

        <div class="container margin10px">
                <table class="normal">
                    <tr>
                        <td class="verticalLine">Выбрано</td>
                        <td class="verticalLine">Что добавить</td>
                        <td class="verticalLine">Рекомендации</td>
                    </tr>
                    <tr>
                        <td class="align-top verticalLine">
                            <table width="100%">
                                <c:forEach items="${pizzasIngr}" var="ingredientHolder">
                                    <tr>
                                        <td>
                                            <c:if test = "${pageContext.response.locale == 'ru'}">
                                                <option>${ingredientHolder.ingredient.nameRus}</option>
                                            </c:if>
                                            <c:if test = "${pageContext.response.locale == 'en'}">
                                                <option>${ingredientHolder.ingredient.nameEng}</option>
                                            </c:if>
                                        </td>
                                        <td>${ingredientHolder.numberOfIngredients}</td>
                                        <td>${ingredientHolder.ingredient.cost} BYN</td>
                                        <td>${ingredientHolder.ingredient.weight} <spring:message code="ingredients.ingredient.gr"/></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                        <td class="align-top verticalLine" rowspan="2">
                            <div class="faq_list">
                                <c:forEach items="${ingredients}" var="entry">
                                    <div class="faq_item">
                                        <div class="faq_item_title">
                                            <div class="faq_item_title_inner">
                                                <c:if test = "${pageContext.response.locale == 'ru'}">
                                                    <option>${entry.key.rusName}</option>
                                                </c:if>
                                                <c:if test = "${pageContext.response.locale == 'en'}">
                                                    <option>${entry.key.engName}</option>
                                                </c:if>
                                            </div>
                                        </div>
                                        <c:forEach items="${entry.value}" var="ingredientsOfCat">
                                            <form:form method="post" class="form-inline" action="/addIngrToPizza/${pizzaId}">
                                                <div class="faq_item_body navbar">
                                                    <table class="normal">
                                                        <tr>
                                                            <td style="width: 150px">
                                                                <c:if test = "${pageContext.response.locale == 'ru'}">
                                                                    <input disabled name="ingredientToAdd" value="${ingredientsOfCat.nameRus}" class="width100 likeCell"/>
                                                                </c:if>
                                                                <c:if test = "${pageContext.response.locale == 'en'}">
                                                                    <input disabled name="ingredientToAdd" value="${ingredientsOfCat.nameEng}" class="width100 likeCell" />
                                                                </c:if>
                                                            </td>
                                                            <td style="width: 80px">${ingredientsOfCat.cost} BYN</td>
                                                            <td>${ingredientsOfCat.weight} <spring:message code="ingredients.ingredient.gr" /></td>
                                                            <td><button class="fa ingredientButton"></button></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </form:form>
                                        </c:forEach>
                                    </div>
                                </c:forEach>
                                <div class="faq_show_all">
                                    <div class="faq_item_title">
                                        <div class="faq_item_title_inner show_all">
                                            Смотреть все
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td class="align-top">
                            <div class="alert alert-warning">
                                <strong>Success!</strong> Indicates a successful fsldkfj x,.nvxgn skl; gh  s;fl
                            </div>
                            <div class="alert alert-info">
                                <strong>Success!</strong> Indicates a successful fsldkfj x,.nvxgn skl; gh  s;fl
                            </div>
                            <div class="alert alert-success">
                                <strong>Success!</strong> Indicates a successful fsldkfj x,.nvxgn skl; gh  s;fl
                            </div>
                            <div class="alert alert-danger">
                                <strong>Success!</strong> Indicates a successful fsldkfj x,.nvxgn skl; gh  s;fl
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Итого ${totalCost} BYN</td>
                        <td>Совет</td>
                    </tr>
                    <tr>
                        <td class="horizontalLine" colspan="3">Готово</td>
                    </tr>
                </table>

        </div>

    </body>
</html>