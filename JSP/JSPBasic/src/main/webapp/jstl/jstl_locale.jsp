<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>locale</title>
</head>
<body>
	<c:set var="now" value="<%=new java.util.Date() %>"></c:set>
	<fmt:setLocale value="en_US"/>
	<div>톰캣 서버 기본 로케일 : <%=response.getLocale() %></div>
	<div>통화 <fmt:formatNumber value="${now }" type="both"></fmt:formatNumber></div>
	
	<hr>
	
	<fmt:setLocale value="en-US"/>
	<c:set var="nowUs" value="<%=new java.util.Date() %>" />
	<div>로케일(us) : <%=response.getLocale() %></div>
	<div>통화 : <fmt:formatDate value="${nowUs }" type="both" dataStyle="full" timeStyle="full"/></div>
	<fmt:timeZone value="GMT-5">
		<div>뉴욕시간 GMT-5 : <fmt:formatDate value="${nowUS }" type="both"/></div>
	</fmt:timeZone>
	
	<hr>
	
	<fmt:setLocale value="en-US"/>
</body>
</html>