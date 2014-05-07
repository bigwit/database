<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header">
	<div class="text-logo">
		Курсовая работа <br>
		"Приложение к базе данных <br>турагенства"
	</div>
	<c:if test="${empty onlineUser}">
		<div class="login-panel">
			<input id="25dfddh6nf" name="login" width="100%" placeholder="логин"/><br>
			<input id="df5h1hyj35fu" name="passwd" width="100%" placeholder="пароль"/><br>
			<a id="signin" href="#" class="head-link color-orange">Войти</a>
			<a href="/database/create" class="head-link color-orange">Регистрация</a>
			<br>
		</div>
	</c:if>
	<c:if test="${not empty onlineUser}">
		<div>
			Welcome, ${onlineUser.getLogin()}
			<!-- добавить линк по которомы выкидивать окно с инфой о юзере -->
		</div>
	</c:if>
</div><br>