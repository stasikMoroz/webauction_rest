<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.03.2019
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>feedbacks</title>
</head>
<body>
    <a href=/busauction_war_exploded/>Назад</a><br>
    <p>Список отзывов по пользователю с id:${user}</p>
    <table>
        <c:forEach items="${list}" var="feedback">
            <tr>
                <td><c:out value="${feedback}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
