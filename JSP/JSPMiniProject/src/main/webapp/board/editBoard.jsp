<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>new writeBoard</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<!-- 로그인 하지 않은 유저는 login.jsp페이지로 이동시키기. -->
<c:if test="${sessionScope.loginMember == null }">
	<c:redirect url="../member/login.jsp"></c:redirect>
</c:if>

<div class="container">
		<h1>writeBoard.jsp</h1>
		<c:out value="${requestScope.board }"></c:out>
		<c:out value="${requestScope.uploadedFile }"></c:out>
		<form action="editBoard.bo?type=edit" method="POST" enctype="multipart/form-data">
			<div class="mb-3 mt-3">
				<label for="writter" class="form-label">작성자</label> 
				<input type="text" class="form-control" id="writter" name="writter" value="${requestScope.board.writter }" readonly>
			</div>
			<div class="mb-3 mt-3">
				<label for="title" class="form-label">제목</label> 
				<input type="text" class="form-control" id="title" name="title" placeholder="Input Title" value="${requestScope.board.title }" >
			</div>
			<div class="mb-3 mt-3">
				<label for="content" class="form-label">내용</label> 
				<textarea type="text" class="form-control" id="content" name="content" placeholder="Input Context" rows="10" style="width:100%;" >${requestScope.board.content }</textarea>
			</div>
			<div class="mb-3 mt-3">
				<label for="upFile" class="form-label">첨부파일</label> 
				<input type="file" class="form-control" id="upFile" name="upFile" value=" " >
			</div>

			<button type="submit" class="btn btn-primary" >수정</button>
			<button type="reset" class="btn btn-danger">취소</button>
		</form>
	</div>

<jsp:include page="../footer.jsp"></jsp:include>
<script type="text/javascript" src="../member/register.jsp"></script>
<script type="text/javascript">

	    // Get a reference to our file input
	    const fileInput = document.querySelector('input[type="file"]');

	    // Create a new File object
	    const myFile = new File(['Hello World!'], '${requestScope.uploadedFile.originalFileName }', {
	        type: 'text/plain',
	        lastModified: new Date(),
	    });

	    // Now let's create a DataTransfer to get a FileList
	    const dataTransfer = new DataTransfer();
	    dataTransfer.items.add(myFile);
	    fileInput.files = dataTransfer.files;
	
// 	    const validation = () => {
// 	    	printErrMsg("title", "Input ID is Unusable. Id is Duplicated", false)
// 	    	return false;
// 	    };

</script>
</body>
</html>