<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty resultset and resultset.size() > 0}">
	<c:forEach var="tourInfo" items="${resultset}">
		<div class="main-result-block">
			<span style="font-size: x-large; margin-left: 10px;">&lt;&lt;
				${tourInfo.getName()} &gt;&gt;</span><br> 
				<span>Куда:${tourInfo.getNamePlace()}</span>
				<br> <span class="tour-info-description">
				<ul>
					<li>Отели: ${tourInfo.getHotelInfo()}</li>
					<li>Перелеты: ${tourInfo.getFlightInfo()}</li>
					<li>Путевки: ${tourInfo.getTravelInfo()}</li>
				</ul>
			</span>
		</div>
		<hr size="2" color="#f47a20" width="75%" align="center">
	</c:forEach>
</c:if>