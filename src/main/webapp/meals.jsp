<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p><a href="meals?action=add">Add meal</a></p>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr>
                <c:set var="color" value="${meal.excess ? 'red' : 'green'}" />

                <td>
                    <span style="color: ${color};">${TimeUtil.dateTimeToString(meal.dateTime)}</span>
                </td>
                <td>
                    <span style="color: ${color};">${meal.description}</span>
                </td>
                <td>
                    <span style="color: ${color};">${meal.calories}</span>
                </td>
                <td><a href="meals?id=${meal.id}&action=edit">Update</a></td>
                <td><a href="meals?id=${meal.id}&action=delete">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>