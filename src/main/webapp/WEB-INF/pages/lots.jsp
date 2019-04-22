<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.02.2019
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>lots</title>
</head>
<body>
    <a href=/busauction_war_exploded/>Назад</a><br>
    Hello from lots.jsp<br>
    Список всех лотов: <br>
    <table>
        <c:forEach items="${Lotlist}" var="lot">
            <tr>
                <td><c:out value="${lot}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
