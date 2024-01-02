<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>application 내장객체</title>
</head>
<body>
	<div>웹 어플리케이션 컨텍스트 패스 : <%=application.getContextPath() %></div>
	<div>웹 어플리케이션 실제(물리적) 저장경로 : <%=application.getRealPath("application.jsp") %></div>
</body>
</html>