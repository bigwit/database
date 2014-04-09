<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=${encoding}">
<link type="text/css" rel="stylesheet" href="resources/css/main.css" />
<link href="http://www.favicon.cc/favicon/593/2/favicon.png"
	rel="shortcut icon" type="image/x-icon" />
<script type="text/javascript" src="resources/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<title>${title}</title>
</head>
<div id="message" class="message">${message}</div>
<body style="min-width: 900px;">
	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<jsp:include page="${Header}"></jsp:include>
	</div>
	<div>
		<hr size="2" color="#EE3344" width="80%" align="center">
	</div>
	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<jsp:include page="${menu}"></jsp:include>
	</div>
	<br>
	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<jsp:include page="${content}"></jsp:include>
	</div>
	<div>
		<hr size="2" color="#DD3344" width="80%" align="center">
	</div>
	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<jsp:include page="${footer}"></jsp:include>
	</div>
</body>
</html>