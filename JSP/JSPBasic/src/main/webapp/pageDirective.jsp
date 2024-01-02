<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page지시자</title>
<script type="text/javascript">
// 	setTimeout(function(){
// 		location.reload();
// 	}, 1000);
</script>
</head>
<body>
<%
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy년 MM월 dd일");
	SimpleDateFormat sdfTime = new SimpleDateFormat("hh시 mm분 ss초");
%>
	오늘은 <b><%=sdfDate.format(cal.getTime()) %></b>
	지금은 <b><%=sdfTime.format(cal.getTime()) %></b>

</body>
</html>