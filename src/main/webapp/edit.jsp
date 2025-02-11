<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Meals</title>
    <style>
        label {
            display: block;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<c:set var="action" value="${param.action}" />
<h2>${"edit".equals(action) ? "Edit" : "Add"} meal</h2>
<section>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">

        <table border="0">
            <tr>
                <td width="150"><label>DateTime: </label></td>
                <td><input type="datetime-local" name="dateTime" size=50 value="${meal.dateTime}" required></td>
            </tr>
            <tr>
                <td><label>Description: </label></td>
                <td><input type="text" name="description" size=50 value="${meal.description}"></td>
            </tr>
            <tr>
                <td><label>Calories: </label></td>
                <td><input type="number" min="0" name="calories" size=50 value="${meal.calories}" required></td>
            </tr>
        </table>

        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
</body>
</html>