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
<% pageContext.setAttribute("msg", "hello"); %>
<div>${pageScope.msg }</div>
<c:set var="msg2" value="피곤해" /> 
${pageScope.msg2 }
<div><c:out value="${msg }" /></div>
<div><c:out value="오늘점심뭐먹지?" /></div>
<c:remove var="msg2"/>
<div>${msg2 }</div>



</body>
</html>