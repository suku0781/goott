<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현식</title>
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
<div><%=str%></div> <!-- 표현식에서는 out.print()로 변환되므로 세미콜론을 입력하지 않아야 한다. -->
<div><%=abs(-4)%></div>
<div><%//이렇게 하면 자바에서 주석이 된다. 이 주석은 브라우저에서 표시되지 않는다. %></div>  
<div><%!//이렇게 하면 자바에서 주석이 된다. 이 주석은 브라우저에서 표시되지 않는다. %></div>  
<%-- JSP주석은 이렇게 적는다. 브라우저에서 표시되지 않는다.  --%>
<!-- HTML 주석은 이렇게 적는다. 브라우저에서 표시된다.  -->
</body>
</html>