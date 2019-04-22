<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.03.2019
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%--TODO удалить--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    <h2>Введите данные пользователя</h2>
    <form:form method="post" action="/busauction_war_exploded/users/executeUpdate">
        <form:input type="hidden" value="${user.id}" path="user.id"/>
        <form:input type="hidden" value="${user.userStatus}" path="user.userStatus"/>
        <p>Имя:<form:input value="${user.name}" path="user.name"/></p>
        <p>Фамилия:<form:input value="${user.surname}" path="user.surname"/></p>
        <p>Логин:<form:input value="${user.login}" path="user.login"/></p>
        <p>Пароль:<form:input value="${user.password}" type="password" path="user.password"/></p>
        <p>email:<form:input value="${user.email}" type="email" path="user.email"/></p>
        <p>Номер телефона:<form:input value="${user.phoneNumber}" type="tel" path="user.phoneNumber"/></p>
        <h3>Адрес</h3>
        <p>Страна:<form:input value="${user.address.state}" path="address.state"/></p>
        <p>Город:<form:input value="${user.address.city}" path="address.city"/></p>
        <p>Улица:<form:input value="${user.address.street}" path="address.street"/></p>
        <p>Дом:<form:input value="${user.address.houseNumber}" path="address.houseNumber"/></p>
        <p>Квартира:<form:input value="${user.address.room}" path="address.room"/></p>
        <h3>Паспортные данные</h3>
        <p>Номер:<form:input value="${user.passport.number}" path="passport.number"/></p>
        <p>Кем выдан:<form:input value="${user.passport.issuedBy}" path="passport.issuedBy"/></p>
        <p>Дата выдачи:<form:input value="${user.passport.dateIssued}" type="date" path="dateIs"/></p>
        <p><input type="submit" value="Обновить"></p>
    </form:form>
    <a href=/busauction_war_exploded/users/all>Назад</a><br>
</body>
</html>
