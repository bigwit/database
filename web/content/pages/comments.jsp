<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="margin-left: 10px; text-align: center;">
	<br>
	<c:if test="${not empty comms and comms.size() > 0}">
		<table class="offices-table comments-table">
			<tr>
				<th>Комментарий</th>
				<th>Оставил</th>
			</tr>
			<c:forEach var="_comm" items="${comms}">
				<tr>
					<td>${_comm.getTextComment()}</td>
					<td>
						${_comm.getClient().getFirstName()}
						${_comm.getClient().getLastName()}. Город: 
						${_comm.getClient().getContact().getLocation().getCity()}
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty comms or comms.size() == 0}">
		Нет комментариев для этого офиса
	</c:if>
</div>