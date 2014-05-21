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
<c:if test="${not empty resultset}">
	<br><a href="#">Показать результаты по ${valueQuery}</a><br>
		
	<br>
	<a href="#">Показать еще</a>
</c:if>

<br>
<br>
<div id="menuSelection" class="select-menu">
	<div data-type-search = "simple" class="selection-element selection-active-element" >Простой поиск</div><br>
	<div data-type-search = "algo1" class="selection-element" >Алгоритм #1</div><br>
	<div data-type-search = "algo2" class="selection-element" >Алгоритм #2</div><br>
	<div data-type-search = "algo3" class="selection-element" >Алгоритм #3</div><br>
</div>