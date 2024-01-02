<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.jsp.dto.ProductDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>application 영역</title>
</head>
<body>
	<h1>application 영역</h1>
	<%
	Map<String, ProductDTO> maps = new HashMap<>();
	maps.put("Galaxy", new ProductDTO("갤럭시", 10, 1353000, "blue"));
	maps.put("iPhone", new ProductDTO("아이폰", 10, 1900000, "blue"));
	
	// application 객체에 바인딩
	application.setAttribute("phones", maps);
	
	
	%>
	
	<div><a href="appPage.jsp">appPage.jsp로 이동</a></div>
</body>
</html>