<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외처리페이지(공통 예외처리)</title>
</head>
<body>
<h1>에러페이지</h1>
<div>에러가 발생하였습니다. 에러가 지속될 경우 건우씨에게 연락주세요...</div>
<%-- <h4><%=exception.getMessage() %></h4> --%>
<h4><%=exception.getMessage() %></h4>
<div><%=exception.getMessage() %></div>
</body>
</html>