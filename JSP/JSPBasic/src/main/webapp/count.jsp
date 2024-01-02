<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%!int globalCnt = 0; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int localCnt = 0;
	
	out.print("<br> localCnt : "+(++localCnt));
	out.print("<br> globalCnt : "+(++globalCnt));
	
	
%>
</body>
</html>