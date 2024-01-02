<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>response객체</title>
</head>
<body>
<h1>mainTest.jsp</h1>
<div>
	<form action="./loginTest.do" method="post">
		<input type="text" name="id" placeholder="Input your Id"><br>
		<input type="text" name="pw" placeholder="Input your PassWord"><br>
		<input type="button" name="joinBtn" onclick="gotoJoin(this)"/>
		<input type="submit">
		
	</form>
	
	<%=session.getAttribute("id") %>	
</div>
</body>
<script>
	function gotoJoin(el){
		if(el.name=="joinBtn"){
			location.href="/JSPBasic/joinTest.jsp";
		}
	}
</script>

</html>