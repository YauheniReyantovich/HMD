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
                            <tr>
                                <td>Название</td>
                                <td>Количество</td>
                                <td>Стоимость</td>
                            </tr>
                            <tr>
                                <td>назв1</td>
                                <td>кол1</td>
                                <td>ст 1</td>
                            </tr>
                            <tr>
                                <td>назв2</td>
                                <td>кол2</td>
                                <td>ст 2</td>
                            </tr>
                            <tr>
                                <td>назв3</td>
                                <td>кол3</td>
                                <td>ст 3</td>
                            </tr>
                        </table>
                    </td>
                    <td class="align-top verticalLine" rowspan="2">
                        <div class="faq_list">
                            <div class="faq_item">
                                <div class="faq_item_title">
                                    <div class="faq_item_title_inner">
                                        Мясо
                                    </div>
                                </div>
                                <div class="faq_item_body">
                                    Свинина
                                </div>
                                <div class="faq_item_body">
                                    Говядина
                                </div>
                                <div class="faq_item_body">
                                    Телятиа
                                </div>
                            </div>
                            <div class="faq_item">
                                <div class="faq_item_title">
                                    <div class="faq_item_title_inner">
                                        Овощи
                                    </div>
                                </div>
                                <div class="faq_item_body">
                                    Помидор
                                </div>
                                <div class="faq_item_body">
                                    <table class="normal">
                                        <tr>
                                            <td>Перец</td>
                                            <td>30 гр</td>
                                            <td>2 руб</td>
                                            <td><i class="fa fa-plus"></i></td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="faq_item_body navbar">
                                    <table class="normal">
                                        <tr>
                                            <td>Лук</td>
                                            <td>300 гр</td>
                                            <td>2 руб</td>
                                            <td><i class="fa fa-plus"></i></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
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
                    <td>Итого</td>
                    <td>Совет</td>
                </tr>
                <tr>
                    <td class="horizontalLine" colspan="3">Готово</td>
                </tr>
            </table>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="../../resources/js/bootstrap.min.js"></script>
        <script src="../../resources/js/main.js"></script>
    </body>
</html>