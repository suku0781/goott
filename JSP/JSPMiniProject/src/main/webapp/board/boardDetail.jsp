<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board detail</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<style type="text/css">
img{
	max-width: fit-content;
/* 	max-	width : width: calc(100vh - 250px); */
}

</style>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>
<c:set var="contextPath" value="<%=request.getContextPath() %>"></c:set>
<c:set var="userId" value="${sessionScope.loginMember.userId }"></c:set>
<c:set var="writter" value="${requestScope.board.writter }"></c:set>
<c:set var="postDate" value="${requestScope.board.postDate }"></c:set>
	<div class="boardList">
		<h1 id="isNewDpTarget">boardDetail.jsp</h1>
		<button type="button" class="btn btn-primary">조회수 <span class="badge badge-pill badge-primary">${requestScope.board.readCount }</span> </button>
		<button type="button" class="btn btn-danger">좋아요 <span class="badge badge-pill badge-primary">${requestScope.board.likeCount }</span> </button>
		<form action="" method="GET" enctype="multipart/form-data">
			<div class="mb-3 mt-3">
				<label for="writter" class="form-label">작성자</label> 
				<input type="text" class="form-control" id="writter" name="writter" value="${board.writter }" readonly>
			</div>
			<div class="mb-3 mt-3">
				<label for="title" class="form-label">제목</label> 
				<div class="form-control" id="title" name="title" >${board.title }</div>
			</div>
			<div class="mb-3 mt-3">
				<label for="content" class="form-label">내용</label> 
				<textarea type="text" class="form-control" id="content" name="content" placeholder="Input Context" rows="10" style="width:100%;" readonly>${board.content }</textarea>
			</div>
			<div class="mb-3 mt-3">
				<c:if test="${requestScope.uploadedFile != null}">
					<label for="upFile" class="form-label">첨부파일</label>
					<div>${requestScope.uploadedFile.originalFileName }</div>
					<img alt="" src="${contextPath }/uploads/${requestScope.uploadedFile.newFileName }" style="">
				</c:if>
			</div>

			<c:if test="${userId eq writter}">
				<button type="button" class="btn btn-primary" onclick="location.href='viewBoard.bo?no=${board.no}&amp;page=editBoard'">수정</button>
				<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">삭제</button>
			</c:if>
			
			<button type="button" class="btn btn-secondary" onclick="location.href='${contextPath }/board/listAll.bo'">목록으로</button>
		</form>
	</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>	
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">알림</h5>
	      </div>
	      <div class="modal-body">
	        ${requestScope.board.no }번 게시글을 삭제하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" onclick="deleteBoard()">삭제</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	
	
	<script type="text/javascript">
	$(function(){
		if(getHowLong() < 5){
			$("#isNewDpTarget").html(`<span class="badge bg-danger ">New</span>` + " " + $("#isNewDpTarget").text());
		}
		
	})
	
	
	
	function getHowLong(){
		let regiDateTime = '<c:out value="${requestScope.board.postDate }"></c:out>';
		let regiDateTime2 = regiDateTime.replaceAll("-", "/").substr(0, regiDateTime.length-2);
		let today = new Date();
		let year = today.getFullYear(); // 년도
		let month = today.getMonth() + 1;  // 월
		let date = today.getDate();  // 날짜
		let hours = today.getHours(); // 시
		let minutes = today.getMinutes();  // 분
		let seconds = today.getSeconds();  // 초
		let dateA = new Date(year+"/"+month+"/"+date+" "+hours+":"+minutes+":"+seconds); // 현재시간
		let dateB = new Date(regiDateTime2); // 타겟 시간
		
		return Math.abs(Math.floor((dateA.getTime() - dateB.getTime()) / (60 * 60 * 1000)))
	}
	
	// 삭제 모달창에서 삭제 버튼을 클릭했을때 이벤트처리 함수
	function deleteBoard(){
		console.log("삭제버튼 클릭함.");
		let obj = {};
		obj.boardNo = '${requestScope.board.no}'
		
		getData("deleteBoard.bo", "GET", obj);
	}
	
	
	function getData(url, type, data, dataType, async){
		let result = false;
		$.ajax({
			url : url,
			type : type || "get",
			data : data || null,
			dataType : dataType || "json",
			async : async || true, 
			success : function(data){
				console.log(data)
				
				if(data.target == "deleteBoard"){
					debugger
					$("#exampleModal").hide();
// 					setTimeout(() => {
// 						$("#exampleModal").show();
						
// 					}, 500);
					location.href="${contextPath }/board/listAll.bo";	
				}
			},
			fail: function(data){
				
			}
		})
		return result;
	}
	
	</script>
</body>
</html>