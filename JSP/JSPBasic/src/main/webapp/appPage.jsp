<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.jsp.dto.ProductDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>application 영역</title>
</head>
<body>
<h1>application영역에서 바인딩한 객체 읽어오기</h1>
<% 
Map<String, ProductDTO> maps = (Map<String, ProductDTO>) application.getAttribute("phones"); 
Set<String> keys = maps.keySet();
for(String key : keys) {
	ProductDTO product = maps.get(key);
	out.print("상품이름 : " + product.getProdName() + "<br>");
	out.print("상품색상 : " + product.getProdColor() + "<br>");
	out.print("상품가격 : " + product.getProdPrice() + "<br>");
	out.print("상품재고 : " + product.getProdNum() + "<br>");
	out.print("<br>");
}

%>
<%-- <%=maps %> --%>

</body>
</html>