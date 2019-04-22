<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.02.2019
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Lots by category</title>
</head>
<body>
    Hello from getLotsByCategory.jsp<br>
    <a href=/busauction_war_exploded/lots/chooseCategory>Назад</a><br>
    Список лотов по категории ${param.cat}: <br>
<table>
    <c:forEach items="${list}" var="lot">
        <tr>
            <td><c:out value="${lot}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
