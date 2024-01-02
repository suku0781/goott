<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 정보</title>
</head>
<body>
<%
long createdTime = session.getCreationTime(); // 세션이 만들어진 시간 밀리초 타임스탬프로 출력 
long lastTime = session.getLastAccessedTime(); // 요청을 마지막으로 보낸 시간 출력
long elapsedTime = lastTime-createdTime;
int inactiveTime = session.getMaxInactiveInterval(); // 유효시간을 초로 반환
boolean sesNew = session.isNew();

out.print("세션아이디 : " + session.getId() + "<br>");
out.print("세션에 머문 시간 : " + elapsedTime + "밀리초" + "<br>");
out.print("세션 유효 시간 : " + inactiveTime+ "초" + "<br>");

if(session.isNew()){
	out.print("새로운 세션이 생성되었습니다.<br>");
} else {
	out.print("새로운 세션이 생성되지 않았습니다.<br>");
}
%>


<!-- getAttribute()메서드 호출해서 session에 바인딩된 정보를 출력해보기 -->
<div>
<% 
Enumeration<String> en = session.getAttributeNames();
while(en.hasMoreElements()){
	String tmp = en.nextElement();
	out.print(tmp + " : ");
	out.print(session.getAttribute(tmp) + "<br>");
// 	String value = session.getParameter(tmp);
}
%>
</div>

</body>
</html>