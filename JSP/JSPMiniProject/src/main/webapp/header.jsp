<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<c:set var="contextPath" value="<%=request.getContextPath() %>"></c:set>
</head>
<style>

.bg-primary{
   	background-image: url("${contextPath }/img/headerImg.jpg");
   	background-position: center;
 	background-size: cover;
}

</style>
<body>
	<div class="p-5 bg-primary text-white text-center">
	  <h1>JSPMiniProject</h1>
	  <p>2024 Jan</p> 
	</div>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <div class="container-fluid">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	        <a class="nav-link active" href="${contextPath }/index.jsp">shk</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Board</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${contextPath }/member/register.jsp">Join</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">login</a>
	      </li>
	    </ul>
	  </div>
	</nav>

</body>
</html>