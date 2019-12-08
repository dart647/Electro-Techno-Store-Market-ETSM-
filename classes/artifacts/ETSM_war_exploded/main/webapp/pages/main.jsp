<%--
  ~ Copyright (c) 2019. Nikita Smalkov
  --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ETSM - Main page</title>
</head>

<body>
<div role="navigation">
    <div class="navbar navbar-inverse">
        <a href="/" class="navbar-brand">Главная</a>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/login">Войти</a></li>
                <li><a href="${pageContext.request.contextPath}/catalog/list">Каталог товаров</a></li>
                <li><a href="${pageContext.request.contextPath}//basket">Корзина</a></li>
                <li><a href="${pageContext.request.contextPath}//user">Личный кабинет</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>