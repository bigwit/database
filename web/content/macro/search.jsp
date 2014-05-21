<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<br>
<br>
<form:form id="searchForm" method="POST" action="/database/sch" commandName="query">
	<form:input path="query" placeholder="${placeholderSearch}" class="query-line" value="${valueQuery}" />
	<form:hidden id="typeOfSeach" path="type" value="simple"/>
	<input type="submit" value="Поиск" class="button orange search-button" />
	<input id="selectionType" type="button" value="v" class="button orange selection-button" />
</form:form>
<br>
<c:if test="${not empty resultset and resultset.size() > 0}">
	<%-- <br><a href="#">Показать результаты по запросу ${valueQuery}</a> --%>
	<br>
		<c:forEach var="tourInfo" items="${resultset}">
			<div class="main-result-block">
				<span style="font-size: x-large; margin-left: 10px;">&lt;&lt; ${tourInfo.getName()} &gt;&gt;</span><br>
				<span>Куда: ${tourInfo.getNamePlace()}</span><br>
				<span class="tour-info-description">
					<ul>
						<li>Отели: ${tourInfo.getHotelInfo()}</li>
						<li>Перелеты: ${tourInfo.getFlightInfo()}</li>
						<li>Путевки: ${tourInfo.getTravelInfo()}</li>
					</ul>
				</span>
			</div>
			<hr size="2" color="#f47a20" width="75%" align="center">
		</c:forEach>
	<br>
	<a href="#">Показать еще</a>
</c:if>

<br>
<br>
<div id="menuSelection" class="select-menu">
	<div data-type-search = "simple" class="selection-element selection-active-element" >Простой поиск</div><br>
	<div data-type-search = "algo1" class="selection-element" >Левенштейна</div><br>
	<div data-type-search = "algo2" class="selection-element" >Джаро-Винклера</div><br>
	<div data-type-search = "algo3" class="selection-element" >Алгоритм #3</div><br>
</div>