<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선언문</title>
</head>
<body>
	<%!
		// 선언문에서 선언된 변수나 메서드들은 jsp페이지가 초기화 될때 초기화되어서 페이지내에서 어떠한 스크립틀릿이나 표현식에도 접근해서 사용할 수 있다. 
		String str = "안녕하세요.";
		public int abs(int n) {
			if(n < 0){
				n = -n;
			} 
			return n;
		}
	%>
	
	<%
		out.print("<div>str</div>");
		out.print("<div>"+abs(str.length())+"</div>");
	%>
</body>
</html>