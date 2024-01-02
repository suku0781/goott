<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script>
		function hello(){
			alert("hi");
		}
	</script>
</head>
<body>
	<h1 onclick="hello()">왜 서블릿을 사용하는가</h1>
	<!--변수에 임의의 값을 저장한 후 그 값이 홀수이면 파란색 글씨로 "홀수입니다." 출력
	변수에 임의의 값을 저장한 후 그 값이 짝수이면 파란색 글씨로 "짝수입니다." 출력 -->
	<%
	int num = 3;
	if(num%2 == 0){ // 짝수
		%><p style="color:red">짝수입니다.</p><%
	} else {
		%><p style="color:blue">홀수입니다.</p><%
	}
	%> 
</body>
</html>