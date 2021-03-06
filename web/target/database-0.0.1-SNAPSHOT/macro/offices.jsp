<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<br>
<c:if test="${not empty offices}">
	<table class="offices-table">
		<tr>
			<th>Название</th>
			<th>Адрес</th>
			<th>Телефон</th>
			<th>E-mail</th>
			<c:if test="${not empty onlineUser}">
					<th>Добавить комментарий ${onlineUser}</th>
			</c:if>
		
		<c:forEach var="office" items="${offices}">
			<tr>
				<td>${office.getName()}</td>
				<td>${office.getContact().getLocation().getCountry()} ${office.getContact().getLocation().getCity()}</td>
				<td>${office.getContact().getPhone()}</td>
				<td>${office.getContact().getEmail()}</td>
				<c:if test="${not empty onlineUser}">
					<td>_link_ Добавить комментарий ${onlineUser}</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</c:if>
<br>