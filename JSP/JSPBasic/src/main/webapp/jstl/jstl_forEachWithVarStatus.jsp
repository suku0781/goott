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
	<table border="1">
		<tr>
			<th>Index</th>
			<th>Count</th>
			<th>hero</th>
		</tr>
		<c:forEach var="hero" items="${heros }" varStatus="status">
			<c:choose>
				<c:when test="${status.index eq 0 }">
					<tr>
						<c:choose >
							<c:when test="${status.index % 2 == 0}"><td style="background-color: yellow; color:'red';" >${status.index }</td></c:when>
							<c:otherwise><td>${status.index }</td></c:otherwise>
						</c:choose>
		<%-- 				<c:if test="${status.index % 2 eq 0 }"><td style="background-color: yellow;">${status.index }</td></c:if> --%>
						<td style="color:'red';">${status.count}</td>
						<td style="color:'red';">${hero }</td>
						
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<c:choose >
							<c:when test="${status.index % 2 == 0}"><td style="background-color: yellow;">${status.index }</td></c:when>
							<c:otherwise><td>${status.index }</td></c:otherwise>
						</c:choose>
		<%-- 				<c:if test="${status.index % 2 eq 0 }"><td style="background-color: yellow;">${status.index }</td></c:if> --%>
						<td>${status.count}</td>
						<td>${hero }</td>
						
					</tr>
				</c:otherwise>	
			</c:choose>
		</c:forEach>
		
	</table>
	
<!-- 	첫번째 tr에 글자색을 빨간색으로 아니면 글자색을 검정색으로 -->
	<table border="1">
		<tr>
			<th>Index</th>
			<th>Count</th>
			<th>hero</th>
		</tr>
		<c:forEach var="hero" items="${heros }" varStatus="status">
		<tr>
			<td>${status.index }</td>
			<td>${status.count}</td>
			<c:if test="${status.index eq 0 }"><td style="color:red;">${hero }</td></c:if>
			<c:if test="${status.index ne 0 }"><td>${hero }</td></c:if>
		</tr>
		</c:forEach>
	</table>
</body>
</html>