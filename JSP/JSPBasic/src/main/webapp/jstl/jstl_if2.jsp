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
<%-- <c:set value="<%  new java.util.Date() %>"></c:set> --%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:out value="${now }"></c:out>

${param.favSeason == '봄' }
<%-- <c:if test="${param.favSeason } == '봄' "></c:if> // 이렇게 하는건 실행되지 않음. --%>
<c:if test="${param.favSeason == '봄' }">
	<c:out value="봄봄봄봄이왔어요"></c:out>${param.nickname }
</c:if>
<c:if test="${param.favSeason == '여름' }">
	<c:out value="여름 여름 여름 여름 아아아아아ㅏ아아아아ㅏ"></c:out>${param.nickname }
</c:if>
<c:if test="${param.favSeason == '가을' }">
	<c:out value="나가을타나봐"></c:out>${param.nickname }
</c:if>
<c:if test="${param.favSeason == '겨울' }">
	<c:out value="함께있단 이유로 행복했었던" ></c:out>${param.nickname }
</c:if>
</body>
</html>