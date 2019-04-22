<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.03.2019
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adding feedback</title>
</head>
<body>
    ${userId}
    <form:form action="/busauction_war_exploded/feedbacks/addFeedback/${userId}" method="post">
        <p>Выберете статус отзыва:</p>
        <select name="stat">
            <c:forEach items="${status}" var="st">
                <option value="${st}" name="status">
                        ${st}
                </option>
            </c:forEach>
        </select>
        <p>Введите текст отзыва:</p>
        <p><textarea rows="10" cols="45" name="text"></textarea></p>
        <p><input type="submit" value="Оставить"></p>
    </form:form>
    <a href=/busauction_war_exploded/>Назад</a><br>
</body>
</html>
