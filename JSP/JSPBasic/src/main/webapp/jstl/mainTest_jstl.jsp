<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>response객체</title>
</head>
<body>
<h1>mainTest_jstl.jsp</h1>
<div>
	<div><% out.print("로그인한 유저 : " + session.getAttribute("id")); %></div><br>
	<div>세션 아이디 : <%= session.getId() %></div>
	
	
	<c:choose>
		<c:when test="${sessionScope.id != null }">
			<input type="submit" value="로그아웃">	
		</c:when>
		<c:otherwise>
			<button onclick="location.href='loginTest_jstl.jsp'">로그인페이지로 이동</button>
		</c:otherwise>
	</c:choose>
	
	
</div>
</body>
</html>