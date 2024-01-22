<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>new writeBoard</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		// 드래그
		$(".upFileArea").on("dragenter dragover", function (e){
			e.preventDefault(); // 진행중인 이벤트 버블링을 캔슬함.
			
			
			
		});
		
		// 드롭
		$(".upFileArea").on("drop", function (e){
			e.preventDefault(); // 진행중인 이벤트 버블링을 캔슬함.
			
			let files = e.originalEvent.dataTransfer.files;
			
			for(let file of files){
				let form = new FormData(); // form태그에서 데이터를 전송해주는 역할을 함.
				form.append("uploadFile", file); // 전송할 upload데이터를 하나씩 추가함. "uploadfile" 파일이름은 컨트롤러단의 MultipartFile매개변수명과 일치시켜야 한다. 
				
				console.log(form);
				
				
				sendData("uploadFile", "POST", form, "json", false);
			}
			
		});
		
	});
	
	// ajax 통신
	function sendData(url, type, data, dataType, async, processData, contentType) {
		$.ajax({
			url : url || "", 
			type : type || "POST", 
			data : data || "null", 
			dataType : dataType || "json", 
			async : async || false,
			processData : processData || false, // false일 경우 textData에 쿼리스트링 처리를 하지 않는다..
			contentType : contentType || false, // false일 경우 enctype="application/x-www-form-urlencoded"(기본값)으로 처리하지 않는다. 
			success : function(e){
				cnosole.log(e)
			},
			error : function(e){
				cnosole.log(e)
			},
			
		})
		
	}
	
	
	
</script>
<style>
	.upFileArea{
		width: 100%;
		height : 200px;
		border: 1px solid;
		font-family: sans-serif;
		font-style: italic;
    color: gray;
    background-color: azure;
    text-align: center;
    line-height: 200px;
	}
</style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<!-- 로그인 하지 않은 유저는 login.jsp페이지로 이동시키기. -->
<%-- <c:if test="${sessionScope.loginMember == null }"> --%>
<%-- 	<c:redirect url="../member/login.jsp"></c:redirect> --%>
<%-- </c:if> --%>

<div class="container">
		<h1>editBoard.jsp</h1>
		
		<form action="writeBoard.bo" method="POST" >
			<div class="mb-3 mt-3">
				<label for="writter" class="form-label">작성자</label> 
				<input type="text" class="form-control" id="writter" name="writter" value="${sessionScope.loginMember.userId }" readonly>
			</div>
			<div class="mb-3 mt-3">
				<label for="title" class="form-label">제목</label> 
				<input type="text" class="form-control" id="title" name="title" placeholder="Input Title" >
			</div>
			<div class="mb-3 mt-3">
				<label for="content" class="form-label">내용</label> 
				<textarea type="text" class="form-control" id="content" name="content" placeholder="Input Context" rows="10" style="width:100%;" ></textarea>
			</div>
			<div class="mb-3 mt-3">
				<label for="upFile" class="form-label">첨부파일</label> 
				<input type="file" class="form-control" id="upFile" name="upFile" >
				<div class="upFileArea" >업로드할 파일을 드래그 앤 드롭하시오.</div>
			</div>

			<button type="submit" class="btn btn-primary">글쓰기</button>
			<button type="reset" class="btn btn-danger">취소</button>
		</form>
	</div>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>