<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립틀릿</title>
</head>
<body>
	<%
	/* 스크립틀릿에서 선언될 변수는 _jspService()의 지역변수로 만들어진다. */
	int num = 3; 
	int num2 = 5;
	int result = num + num2;
	// 어?> 이것도 보이나?
	out.println(); // system.out.print 할때 out 이 아님. jsp Writer의 out임.
	out.println("<div>"+num+" + "+num2+" = "+result+"</div>");
	
	%>
	
</body>
</html>