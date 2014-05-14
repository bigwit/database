<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<br>
<c:if test="${not empty offices}">
	<table class="offices-table">
		<tr>
			<th>Название</th>
			<th>Адрес</th>
			<th>Телефон</th>
			<th>E-mail</th>
			<c:if test="${not empty onlineUser}">
				<th>Комментарии</th>
			</c:if>

			<c:forEach var="office" items="${offices}">
				<tr>
					<td>${office.getName()}</td>
					<td>${office.getContact().getLocation().getCountry()}
						${office.getContact().getLocation().getCity()}
						${office.getContact().getLocation().getDescription()}</td>
					<td>${office.getContact().getPhone()}</td>
					<td>${office.getContact().getEmail()}</td>
					<c:if test="${not empty onlineUser}">
						<td>
							<span id="${office.getId()}" data-add-comment class="office-comments-link">Добавить </span>|
							<span id="${office.getId()}" data-show-comment class="office-comments-link"> Просмотреть</span>
						</td>
					</c:if>
				</tr>
			</c:forEach>
	</table>
</c:if>
<br>
<div id="add_comm_office" style="display: none;">
	<div style="margin: 10px;"> <br> <span style="font-size: x-large;">Заголовок</span>
	<br>
		<input data-header-comment="on" style="width: 80% !important; " placeholder="Введите заголовок комментария" />
		<br> <br> <span style="font-size: x-large;">Текст комментария</span><br> 
		<textarea placeholder="Введите комментарий" style="width: 80%; height: 150px; max-height: 200px; max-width: 80%;"></textarea> <br>
		<input type="button" value="Добавить" class="button orange" onclick="window.send(this, {type : 'comment'});" />
	</div>
</div>

