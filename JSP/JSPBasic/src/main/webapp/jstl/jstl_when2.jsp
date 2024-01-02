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
<c:choose>
	<c:when test="${param.favSeason == '봄' }">
		<!-- 조건이 참일 동안 실행할 문장. -->
		<div>봄을 좋아하시네요.</div>
	</c:when>
	<c:when test="${param.favSeason == '여름' }">
		<div>여름을 좋아하시네요.</div>
		
	</c:when>
	<c:when test="${param.favSeason == '가을' }">
		<div>가을을 좋아하시네요.</div>
		
	</c:when>
	<c:otherwise>
		<div>겨울을 좋아하시네요.</div>
	</c:otherwise>
		
</c:choose>

</body>
</html>