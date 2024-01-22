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
   	background-image: url("${contextPath }/resources/img/headerImg.jpg");
   	background-position: center;
 	background-size: cover;
}
.userImg{
	width: 25px;
	height: 25px;
	border-radius: 25px;
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
	        <a class="nav-link active" href="${contextPath }/">shk</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${contextPath }/board/listAll">Board</a>
	      </li>
	      
	      <c:choose> <%-- 로그인 하지 않은 경우 --%>
	      	<c:when test="${sessionScope.loginMember == null }">
      		  <li class="nav-item">
		        <a class="nav-link" href="${contextPath }/member/register.jsp">Join</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${contextPath }/member/login.jsp">login</a>
		      </li>
	      	</c:when>
	      	<c:otherwise><%-- 로그인한 경우 --%>
	      	  <li class="nav-item" style="color:white">
		        <a class="nav-link" href="${contextPath }/member/myPage.mem?userId=${sessionScope.loginMember.userId}">${sessionScope.loginMember.userId }
		        	<img alt="" src="${contextPath }/${sessionScope.loginMember.isAdmin }" class="userImg">
		        </a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="${contextPath }/member/logout.mem">logout</a>
		      </li>
	      	</c:otherwise>
	      </c:choose>
	      
	      <c:if test="${sessionScope.loginMember.memberImg == 'y' }">
	      	<li class="nav-item">
	      	<a class="nav-link" href="${contextPath }/admin/admin.jsp">adminPage</a>
	      	</li>
	      </c:if>
<!-- 	      과제 -->
<!-- 	      로그아웃 기능 구현  -->
<!-- 	      마이페이지 기능 구현 (적립금, 사용자 정보 출력 ) -->
	      
	      
	    </ul>
	  </div>
	</nav>
	<script type="text/javascript">
// 		$(function(){
			
// 			// 이미지를 클릭할 경우 마이페이지 정보 수정 페이지로 이동
// 			$(".userImg").click(function(e){
// 			    console.log(e)
// 			})
// 		})
		
	</script>
</body>
</html>