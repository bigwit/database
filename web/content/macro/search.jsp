<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<br>
<br>
<form:form id="searchForm" method="POST" action="/database/sch" commandName="query">
	<form:input path="query" placeholder="${placeholderSearch}" class="query-line" />
	<input type="submit" value="Поиск" />
</form:form>
<br>
<br>
