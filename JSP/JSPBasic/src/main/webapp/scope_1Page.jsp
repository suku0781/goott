<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<b>Scope_1Page</b>
	<br>
	<%
	// 각 내장 객체의 영역에 정보 바인딩
	pageContext.setAttribute("name", "page data");
	request.setAttribute("name", "request data");
	session.setAttribute("name", "session data");
	application.setAttribute("name", "application data");
	
	// 각 내장 객체의 영역에 바인딩된 정보 확인
	out.print("pageContext : " + pageContext.getAttribute("name") + "<br>");
	out.print("request : " + request.getAttribute("name") + "<br>");
	out.print("session : " + session.getAttribute("name") + "<br>");
	out.print("application : " + application.getAttribute("name") + "<br>");
	
// 	request.getRequestDispatcher("scope_2Page.jsp").forward(request, response); // url은 안바뀌고 scope_2Page로 이동한다. 
	response.sendRedirect("scope_2Page.jsp");
	
	%>
	<a href="scope_2Page.jsp">scope_2Page.jsp로 이동</a>
</body>
</html>