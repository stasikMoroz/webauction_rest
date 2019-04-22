<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.02.2019
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация пользователя</title>
</head>
<body>
    <h2>Введите данные пользователя</h2>
    <form:form method="post" action="/busauction_war_exploded/users/addUser">
        <p>Имя:<form:input path="user.name"/></p>
        <p>Фамилия:<form:input path="user.surname"/></p>
        <p>Логин:<form:input path="user.login"/></p>
        <p>Пароль:<form:input type="password" path="user.password"/></p>
        <p>email:<form:input type="email" path="user.email"/></p>
        <p>Номер телефона:<form:input type="tel" path="user.phoneNumber"/></p>
        <h3>Адрес</h3>
        <p>Страна:<form:input path="address.state"/></p>
        <p>Город:<form:input path="address.city"/></p>
        <p>Улица:<form:input path="address.street"/></p>
        <p>Дом:<form:input path="address.houseNumber"/></p>
        <p>Квартира:<form:input path="address.room"/></p>
        <h3>Паспортные данные</h3>
        <p>Номер:<form:input path="passport.number"/></p>
        <p>Кем выдан:<form:input path="passport.issuedBy"/></p>
        <p>Дата выдачи:<form:input type="date" path="dateIs"/></p>
        <p><input type="submit" value="Зарегистрировать"></p>
    </form:form>
    <a href=/busauction_war_exploded/>Назад</a><br>
</body>
</html>
