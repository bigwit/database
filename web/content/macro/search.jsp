<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<br>
<br>
<form:form id="searchForm" method="POST" action="/database/sch" commandName="query">
	<form:input path="query" placeholder="${placeholderSearch}" class="query-line" value="${valueQuery}" />
	<input type="submit" value="Поиск" class="button orange" />
</form:form>
<br>Здесь предложение поискать по оригинальному запросу<br><br>
<c:if test="${not empty resultset}">
	<c:forEach var="resultItem" items="${resultset}">
		Категория: ${resultItem.getCategory().getDisplayName()}<br>
		<table >
			<tr>
			<c:forEach var="str" items="${resultItem.getContent()}">
				<td style="margin: 10px;">
					${str}
				</td>
			</c:forEach>
			</tr>
		</table>
	</c:forEach>
</c:if>
<br>
Показать еще
<br>
<br>
