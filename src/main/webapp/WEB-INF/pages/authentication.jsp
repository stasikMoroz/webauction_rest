<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.03.2019
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>authentication</title>
</head>
<body>
    <form action="/busauction_war_exploded/users/checkUser" method="post">
        <p>Логин:<input type="text" size="40" name="login"></p>
        <p>Пароль:<input type="password" size="40" name="password"></p>
        <p><input type="submit" value="Проверить"></p>
    </form>
    <a href=/busauction_war_exploded/>Назад</a><br>
</body>
</html>
