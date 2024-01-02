<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POST방식으로 요청</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	function callServletByAjax(){
		$.ajax({
		    url: "HelloServlet_POST", 
		    type: 'POST',
		    data: {
		    	"name" : "김수혁",
		    	"age" : 27
		    },
		    success : function (data){
		      console.log('success',data);
				document.getElementById("ajaxTest").innerHTML = data;
		    },
		    fail : function (data){
		      console.log('fail',data);
		    },
		
		  })
		
	}
</script>
<body>
	<form action="./HelloServlet_POST" method="POST">
		<div > 이름 : <input type="text" name="name"></div>
		<div > 나이 : <input type="text" name="age"></div>
		<input type="submit" value="전송">
	</form> 
	<div id="ajaxTest"></div>
	<button onclick="callServletByAjax()">Ajax로 서블릿 요청(GET방식)</button>		
</body>
</html>