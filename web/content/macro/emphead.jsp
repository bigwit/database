<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="header">
	<div class="text-logo">
		BestTour <br>
		 <br>
		Панель управления
	</div>
	<c:if test="${not empty onlineUser}">
		<div class="login-panel">
			<br>
			${onlineUser.getLogin()}(${onlineUser.getPeople().getFirstName()} ${onlineUser.getPeople().getLastName()})
			<br>
			<br>
		</div>
	</c:if>
</div><br>