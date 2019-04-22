<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.02.2019
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>users</title>
</head>
<body>
    <a href="/busauction_war_exploded/">Назад</a><br>
    Hello from user.jsp<br>
    Список всех пользователей: <br>
    <table>
        <c:forEach items="${list}" var="user">
            <tr>
                <td><c:out value="${user}"/><br>
                    <a href="/busauction_war_exploded/users/delete/${user.getId()}">Удалить пользователя</a>
                    &nbsp;
                    <a href="/busauction_war_exploded/users/update/${user.getId()}">Обновить пользователя</a>
                    &nbsp;
                    <a href="/busauction_war_exploded/feedbacks/all/${user.getId()}">Показать отзывы</a>
                    &nbsp;
                    <a href="/busauction_war_exploded/feedbacks/add/${user.getId()}">Добавить отзыв</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
