<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<%
	String[] heros = {"캡틴아메리카", "헐크", "아이언맨", "스파이더맨", "토르"};
	pageContext.setAttribute("heros", heros);
%>

<ul>
	<c:forEach var="hero" items="${heros }">
		<li>${hero }</li>
	</c:forEach>
</ul>

<!-- begin : 시작값 , end : 끝값 , [step : 증감치 (생략가능)] -->
<!-- 0부터 10까지 모두 찍힌다. -->
<c:forEach var="index" begin="0" end="10">
	<li>${index }번재 li</li>
</c:forEach>

<hr>

<ul>
	<c:forEach var="i" begin="1" end="9">
		<div>
			<c:forEach var="j" begin="1" end="9">
			<fmt:formatNumber var="no" minIntegerDigits="2" value="${j*i}" type="number"></fmt:formatNumber>
				${j} * ${i} = ${no}
			</c:forEach>  
			 
		</div>
	</c:forEach>
	
</ul>
</body>
</html>