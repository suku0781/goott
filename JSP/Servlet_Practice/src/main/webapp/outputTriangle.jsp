<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삼각형</title>
</head>
<script type="text/javascript">
	function validation() {
		isValid = false;
		
		let base = document.getElementById("base").value;
		let height = document.getElementById("height").value;
		
		console.log(base, height);
		
		if(base <= 0 || height <= 0){
			alert("입력값이 유효하지 않습니다.")
			return false;
		} else {
			return true;
		}
	}
</script>
<body>
<!-- 1. jsp에서 삼각형의 밑변과 높이를 입력받아서 서블릿에서 그 데이터를 받아 삼각형의 넓이를 구하고, 웹페이지로 삼각형의 밑변, 높이, 넓이를 출력하는 프로그램을 작성하기(GET 방식)  -->
	<form action="./Triangle" method="GET">
		<p>삼각형 밑변 </p> <input type="text" name="base" id="base">
		<p>삼각형 높이 </p> <input type="text" name="height" id="height">
		<input type="submit" onclick="return validation();">
	</form>
	
<!-- 2. 유저에게 이름, 아이디, 비밀번호를 입력받아 서블릿을 통하여 입력받은 이름, 아이디, 비밀번호를 웹페이지로 출력하는 프로그램을 작성하기(POST 방식) -->
	<form action="./Triangle" method="POST">
		<p>이름</p> <input type="text" name="name">
		<p>아이디</p> <input type="text" name="id">
		<p>비밀번호</p> <input type="password" name="pw">
		<input type="submit">
	</form>

</body>
</html>