<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="emp-main">
	<div class="emp-menu">
		<div id="clients" class="orange">Клиенты</div>
		<div id="addPlace" class="orange">Добавить место</div>
		<div id="addTour" class="orange">Добавить тур</div>
		<div id="addHotel" class="orange">Добавить отель</div>
		<div id="addFlight" class="orange">Добавить транспорт</div>
		<div id="createTravel" class="orange">Оформить путевку</div>
		<div id="personalInfo" class="orange">Личные данные</div>
		<div id="exit" class="orange">Выход</div><br>
	</div>
	<div id="empViewport" class="emp-content">
		<jsp:include page="${empMacros}"></jsp:include>
	</div>
</div>