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
}
.boardList{
	margin: 0 50px;
}
#reply{
	width: 100%;
}
.reply{
	margin: 20px 0;
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
		
		<input type="hidden" value="${requestScope.board.likeCount }">
		
		<button type="button" class="btn btn-primary">조회수 <span class="badge badge-pill badge-primary">${requestScope.board.readCount }</span> </button>
		<button type="button" class="btn btn-danger" onclick="likeButtonClick()">좋아요 <span class="badge badge-pill badge-primary">${requestScope.board.likeCount }</span> </button>
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
			<div class="mb-3 mt-3" style="display: -webkit-box;">
				<label for="upFile" class="form-label">첨부파일(${requestScope.uploadedFileList.size() })</label>
				<c:if test="${requestScope.uploadedFileList.size() != 0}">
					<c:forEach var="file" items="${requestScope.uploadedFileList }">
						<c:choose>
							<c:when test="${file.thumbFileName != null }">
								<div style="text-align: center; margin: 0 10px;">
									<img alt="" src="../resources/uploads${file.thumbFileName }">
									<p>${file.originalFileName }</p>								
								</div>
							</c:when>
							<c:otherwise>
								<div style="text-align: center; margin: 0 10px;">
									<img alt="" src="../resources/img/file.png" style="width: 50px;">
									<p>${file.originalFileName }</p>								
								</div>
							</c:otherwise>
						</c:choose>
						
					</c:forEach>
				</c:if>
			</div>

			<c:if test="${userId eq writter}">
				<button type="button" class="btn btn-primary" onclick="location.href='editBoard?no=${board.no}'">수정</button>
				<button type="button" class="btn btn-danger" onclick="location.href='deleteBoard?no=${board.no}'" data-bs-toggle="modal" data-bs-target="#exampleModal">삭제</button>
			</c:if>
				
			<button type="button" class="btn btn-secondary" onclick="location.href='${contextPath }/board/listAll'">목록으로</button>
		</form>
		<div class="mb-3 mt-3">
			<label for="reply" class="form-label" id="replyCount">댓글()</label> 
			<div style="display:flex">
				<textarea rows="3" cols="30" id="reply" name="reply" placeholder="Input Reply Context"></textarea>
				<button type="button" class="btn btn-primary" onclick="saveReply()" style="width: 100px;">답글달기</button>
			</div>
			<div id="replyTag"></div>
		</div>
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

		let status = '${param.status}';
		status == "noPermission" ? alert("수정 권한 없음.") : "";
		
		// 댓글 정보 불러오기
		getData("/reply/all/${board.no}");
		
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
		
// 		getData("deleteBoard", "GET", obj);
	}
	
	function submitReply(){
		let obj = {};
		obj.writter = '${board.writter}';
		obj.content = $("#reply").val();
		obj.ref = '${board.ref}';
		obj.step = '${board.step}';
		obj.refOrder = '${board.refOrder}';
		obj.boardNo = '${board.no}';
		
		console.log(obj)
		
// 		getData("reply", "POST", obj, "json", false);
	}
	
	
	function getData(url, type, data, dataType, async){
		$.ajax({
			url : url,
			type : type || "get",
			data : data || null,
			dataType : dataType || "json",
			async : async || true, 
			success : function(data){
				console.log(data)
				outputAllReplies(data);
			},
			fail: function(data){
				
			}
		})
 	}
	
	function likeButtonClick(){
		console.log("조항요!");
// 		let obj = {};
// 		obj.no = '${board.no}';
// 		obj.userId = '${sessionScope.loginMember.userId}' || "jkl123";
		
		
	}
	
	function outputAllReplies(data){
		let html = '';
		let replyCount = 0;
// 		debugger
		if(data.length){ // 댓글이 있으면 출력
			$.each(data, function(index, item){
					html += `<div id="reply\${index}" class="reply">
										<tr>
											<td>
													<img alt="" src="../resources/img/user.png" width="30" style="border-radius: 50%;">
													&nbsp;\${item.replier}&nbsp;&nbsp;`+outputSimpleTime(item.postDate)+`
											</td>
											<td>
												<div>
													<div id="replyText" style="margin-left: 40px;">\${item.replyText}</div>
												</div>
												<div>
													<div id="replymodi" style="margin-left: 40px;"><img alt="modify" src="../resources/img/modify.png" width="25" onclick="modifyReply(reply\${index})"><img alt="delete" src="../resources/img/delete.png" width="12" onclick="deleteReply(reply\${index})"></div>
												</div>
											</td>
										</tr>
									 </div>`;
									 replyCount = index;
			});
			
		} else { // 없으면 댓글없음 출력
			html += `<div>댓글없음.</div>`;
		}
		$("#replyTag").html(html);
		$("#replyCount").html("댓글("+data.length+")");
	}
	
	// 몇 일전, 몇 시간전, 몇 분전, 방금전 출력
	function outputSimpleTime(timestamp){
		let postDate = new Date(timestamp);
		let now = new Date(); // 현재날짜시간
		
		let diff = (now - postDate) / 1000;// 시간차 (초단위)
		
		let times = [
				{name : "일", time : 24 * 60 * 60},
				{name : "시간", time : 60 * 60},
				{name : "분", time : 60}
		];
		
		for(let val of times){
			let betweenTime = Math.floor(diff / val.time);
			
			if(betweenTime > 0){
				if(diff > 24 * 60 * 60){
					return postDate.toLocaleString();
				}
				
				return betweenTime + val.name + " 전";
			}
		}
		
		return "방금전";
	}

	function saveReply(){
		debugger
		let parentNo = '${board.no}';
		let replyText = $("#reply").val();
		let replier = '${sessionScope.loginMember.userId}'; // 로그인유저이지만 임시로 j1kl23
		let newReply = {
				"parentNo" : parentNo,
				"replyText" : replyText,
				"replier" : replier,
			};
		
		if(replier == ""){
			location.href="/member/login?redirectURL=viewBoard&no="+parentNo;
		} else {
			
			$.ajax({
				url : "/reply/",
				type : "POST",
				data : JSON.stringify(newReply),
				headers : {
					"Content-type" : "application/json", // 송신하는 데이터의 타입(MIME-TYPE)
					"x-HTTP-Method-Override" : "POST", // 과거의 웹브라우저에서 POST방식으로 동작하도록 한다.
				},
				dataType : "text",
				success : function(data){
					console.log("data : "+data)
					if(data == "success"){
						getData("/reply/all/${board.no}");
						$("#reply").val("");
					}
				},
				error: function(data){
					console.error("error! : "+data);
				}
			});
			
		}
		
		
	}
	
	</script>
</body>

</html>