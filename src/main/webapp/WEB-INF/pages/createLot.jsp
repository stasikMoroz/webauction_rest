<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.02.2019
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creation of Lot</title>
</head>
<body>
    <p>Форма создания лота</p>
    <form:form action="/busauction_war_exploded/lots/addLot" method="post">
        <p>Описание:<form:input type="text" size="40"  path="description"/></p>
        <p>Стартовая цена:<form:input type="text" size="40"  path="startPrice"/></p>
        <p>Категория:<form:select name="cat" path="category">
            <c:forEach items="${categories}" var="catValue">
                <option value="${catValue}" name="category">
                    ${catValue}
                </option>
            </c:forEach>
        </form:select></p>
        <p><input type="submit" value="Создать лот"></p>
        <a href=/busauction_war_exploded/>Назад</a><br>
    </form:form>
</body>
</html>
