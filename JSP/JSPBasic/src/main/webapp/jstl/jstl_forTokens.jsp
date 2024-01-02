<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String heros = "캡틴아메리카, 헐크, 아이언맨, 스파이더맨, 토르";
	pageContext.setAttribute("heros", heros);
%>
	<ul>
		<c:forTokens var="hero" items="${heros }" delims=","> <%-- split(',') 과 유사한 개념--%>
			<li>${hero }</li>
		</c:forTokens>
	</ul>
</body>
</html>