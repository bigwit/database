<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/main.css" />
<link type="text/css" rel="stylesheet" href="resources/css/emp.css" />
<link href="http://www.favicon.cc/favicon/593/2/favicon.png"
	rel="shortcut icon" type="image/x-icon" />
<script type="text/javascript" src="resources/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="resources/js/grlib.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<script type="text/javascript" src="resources/js/emp.js"></script>
<title>Insert title here</title>
</head>
<body style="min-width: 900px;">
	<div id="message" class="message">${message}</div>
	<div align="center" style="margin-left: 5%; margin-right: 5%;">
		<jsp:include page="/macro/emphead.jsp"></jsp:include>
	</div>
	<div>
		<hr size="2" color="#f47a20" width="90%" align="center">
	</div>
	<div style="margin-left: 5%; margin-right: 5%;">
		<jsp:include page="${content}"></jsp:include>
	</div>
	<br>
</body>
</html>