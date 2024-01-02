<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>당신이 좋아하는 계절은 <%=request.getParameter("favSeason") %>입니다.</div>
<div>당신의 별명은 <%=request.getParameter("nickName") %>입니다.</div>
<hr>
<div>${param.nickName }이 좋아하는 계절은 ${paramValues.favSeason[0] }입니다.</div>
</body>
</html>