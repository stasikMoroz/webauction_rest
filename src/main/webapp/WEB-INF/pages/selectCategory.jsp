<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.02.2019
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>select category</title>
</head>
<body>
    <form action="/busauction_war_exploded/lots/getLotsByCategory" method="post">
        <p>Выберете категорию для поиска:</p>
            <select name="cat">
                <c:forEach items="${categories}" var="catValue">
                    <option value="${catValue}" name="category">
                            ${catValue}
                    </option>
                </c:forEach>
        </select>
        <p><input type="submit" value="Найти"></p>
        <a href=/busauction_war_exploded/>Назад</a><br>
    </form>
</body>
</html>
