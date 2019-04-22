<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.03.2019
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select status</title>
</head>
<body>
    <form action="/busauction_war_exploded/users/allByStatus" method="post">
        <p>Выберете категорию для поиска:</p>
        <select name="stat">
            <c:forEach items="${status}" var="st">
                <option value="${st}" name="status">
                        ${st}
                </option>
            </c:forEach>
        </select>
        <p><input type="submit" value="Найти"></p>
        <a href=/busauction_war_exploded/>Назад</a><br>
    </form>
</body>
</html>
