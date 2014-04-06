<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<%request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=${encoding}">
<title>  ${title} </title>
</head>
<body style="min-width: 900px;">
	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<jsp:include page="${Header}"></jsp:include>
	</div>
	<div><hr size="2" color="#EE3344" width="80%" align="center" ></div>
	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<jsp:include page="${menu}"></jsp:include>
	</div>
	<br>
	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<jsp:include page="${content}"></jsp:include>
	</div>
	<div><hr size="2" color="#DD3344" width="80%" align="center"></div>
	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<jsp:include page="${footer}"></jsp:include>
	</div>
</body>

<style>

.header {
	margin: 0px;
	padding: 0px;
}

.footer {
	
}

.text-logo {
	margin-left:20px;
	font-family: monospace;
	font-size: xx-large;
	float: left;
}

.login-panel {
	top: 10px;
	border: solid 1px;
	border-color: black;
	border-radius: 5px;
	width: 200px;
	float: right;
	padding-top: 5px;
	margin-bottom: 2px;
}

.menu-button {
	border: solid 1px;
	border-color: #DD3344;
	border-bottom-right-radius: 5px;
	border-bottom-left-radius: 5px;
	margin-right: 2%;
	margin-top: 0px;
	height: 25px;
	background: #DD3344;
	padding-top: 8px;
	padding-bottom: 8px;
	padding-left: 15px;
	padding-right: 15px;
}

.head-link {
	padding-left: 5px;
	padding-right: 5px;
	padding-top: 2px;
	padding-bottom: 2px;
	color: #DD3344;
}

.link-image {
	width: 100px;
	height: 50px;
	padding: 5px;
}

</style>

</html>