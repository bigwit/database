<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="text-align: left; margin: 10px;">
	<c:if test="${not empty onlineUser}">
		<table style="width: 100%;">
			<tr>
				<td>Логин</td>
				<td>${onlineUser.getLogin()}</td>
			</tr>
			<tr>
				<td>Имя</td>
				<td>${onlineUser.getPeople().getFirstName()}</td>
			</tr>
			<tr>
				<td>Фамилия</td>
				<td>${onlineUser.getPeople().getLastName()}</td>
			</tr>
			<tr>
				<td>Дата рождения</td>
				<td>${onlineUser.getPeople().getDateBirth().toString()}</td>
			</tr>
			<tr>
				<td>Пол</td>
				<td>${onlineUser.getPeople().getPresentationSex()}</td>
			</tr>
			<tr>
				<td>Телефон</td>
				<td>${onlineUser.getPeople().getContact().getPhone()}</td>
			</tr>
			<tr>
				<td>Електронная почта</td>
				<td>${onlineUser.getPeople().getContact().getEmail()}</td>
			</tr>
			<tr>
				<td>Адрес</td>
				<td>${onlineUser.getPeople().getContact().getLocation().getCountry()}. 
				    ${onlineUser.getPeople().getContact().getLocation().getCity()}. 
				    ${onlineUser.getPeople().getContact().getLocation().getDescription()}.</td>
			</tr>
		</table>
	</c:if>
</div>