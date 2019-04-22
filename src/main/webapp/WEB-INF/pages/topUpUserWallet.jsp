<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.02.2019
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>top up wallet</title>
</head>
<body>
    <form action="/busauction_war_exploded/users/toppedUp" name="wallet" method="post">
        <p>Id пользователя:<input type="text" size="40" name="id"></p>
        <p>Сумма:<input type="text" size="40" name="sumOfMoney"></p>
        <p><input type="submit" value="Пополнить"></p>
    </form>
    <a href=/busauction_war_exploded/>Назад</a><br>
</body>
</html>
