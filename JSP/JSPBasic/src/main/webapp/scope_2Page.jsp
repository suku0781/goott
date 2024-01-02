<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<b>Scope_2Page</b>
	<br>
	<%
	
	// 각 내장 객체의 영역에 바인딩된 정보 확인
	out.print("pageContext : " + pageContext.getAttribute("name") + "<br>");
	out.print("request : " + request.getAttribute("name") + "<br>");
	out.print("session : " + session.getAttribute("name") + "<br>");
	out.print("application : " + application.getAttribute("name") + "<br>");
	%>
	<a href="scope_3Page.jsp">scope_3Page.jsp로 이동</a>
</body>
</html>