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
	<c:set var="now" value="<%=new java.util.Date() %>"></c:set>
	<div>${now }</div>
	<div><fmt:formatDate value="${now }" type="time"/></div>
	<div><fmt:formatDate value="${now }" type="date"/></div>
	<div><fmt:formatDate value="${now }" type="both"/></div>
	
	<div>time(medium) : <fmt:formatDate value="${now }" type="time" timeStyle="medium"/></div>
	<div>time(long) : <fmt:formatDate value="${now }" type="time" timeStyle="long"/></div>
	<div>date(medium) : <fmt:formatDate value="${now }" type="date" dateStyle="medium"/></div>
	<div>date(long) : <fmt:formatDate value="${now }" type="date" dateStyle="long"/></div>
	
	<div>date : <fmt:formatDate value="${now }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
</body>
</html>