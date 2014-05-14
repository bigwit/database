<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<br>
<c:if test="${not empty comms and comms.size() > 0}">
<table class="offices-table">
	<tr>
		<th>Комментарий</th>
		<th>Оставил</th>
	</tr>
	<c:forEach var="comm" items="${comms}">
		<tr>
			<th>${comm.getTextComment()}</th>
			<td>${comm.getClient()getPeople()getFirstName()} 
				${comm.getClient()getPeople()getLastName()}</td>
		</tr>
	</c:forEach>
</table>
</c:if>
<c:if test="${empty comms or comms.size() > 0}">
	Нет комментариев для этого офиса
</c:if>