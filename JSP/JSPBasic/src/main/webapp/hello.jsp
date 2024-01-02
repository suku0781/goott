<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World</h1>
<%-- 	<div>로그인 한 유저 : <%=session.getAttribute("loginMemberId") %></div> --%>
	<div><% out.print("로그인 한 유저 : " + session.getAttribute("loginMemberId"));%></div>
	<div>로그인 한 유저 세션 아이디 : <%=session.getId() %></div>
</body>
</html>