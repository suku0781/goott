<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>response객체</title>
<script type="text/javascript">
// 	location.search.split('=')[1] == "loginSuccess" ? alert("로그인 성공") : alert("로그인 실패");
	window.onload = function() {
		if(getParameter('status') == 'loginSuccess'){
			alert("로그인성공.");
		} else {
			alert("로그인실패.");
		}
		
	}
	
	// 선생님 코드
	function getParameter(paraName) {
	  // 쿼리스트링에서 파라미터 값 얻어오기.
	  // 만약 쿼리스트링에 paraName이 없다면 null을 반환한다.
	  let returnVal = null;
	  let url = location.href;

	  if (url.indexOf("?") != -1) {
	    // 쿼리 스트링이 있는 경우
	    let queryString = url.split("?")[1];
	    let queryStringArr = queryString.split("&");

	    for (let item of queryStringArr) {
	      if (item.split("=")[0] == paraName) {
	        returnVal = item.split("=")[1];
	        break; // 파라미터 값을 찾으면 해당 반복문 블럭을 빠져나감
	      }
	    }
	  }

	  return returnVal;
	}
</script>
</head>
<body>
<h1>mainTest.jsp</h1>
<div>
<div><% out.print("로그인한 유저 : " + session.getAttribute("loginMemberId")); %></div><br>
<div><% out.print("로그인한 유저비번 : " + session.getAttribute("loginMemberPw")); %></div><br>
<div>세션 아이디 : <%= session.getId() %></div>
	<a href="loginTest1.jsp">로그인</a>
	<a href="loginTest2.jsp">세션 로그인2</a>
<!-- 	<form action="./sessionLogout.do" method="get"><input type="submit" value="로그아웃"></form> -->
	<button type="submit" formaction="./sessionLogout.do">제출</button>
	<a href="hello.jsp">hello.jsp로 이동.</a>
	<div><a href="checkSession.jsp">checkSession.jsp로 이동</a></div>
	<%
	if(session.isNew()){
		out.print("새로운 세션이 생성되었습니다.<br>");
	} else {
		out.print("새로운 세션이 생성되지 않았습니다.<br>");
	}
	%>
	<%= request.getParameter("status") %>
	
	
</div>
</body>
</html>