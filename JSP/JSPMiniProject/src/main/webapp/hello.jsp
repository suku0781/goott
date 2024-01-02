<%@page import="java.sql.Connection"%>
<%@page import="com.miniPrj.dao.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>hello jsp!</h4>
	<%
		Connection con = DBConnection.getInstance().dbConnect();
		out.print(con.toString());
		con.close();
	%>
</body>
</html>