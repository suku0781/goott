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
			data : data || null, 
			dataType : dataType || "json", 
			async : async || false,
			processData : processData || false, // false일 경우 textData에 쿼리스트링 처리를 하지 않는다..
			contentType : contentType || false, // false일 경우 enctype="application/x-www-form-urlencoded"(기본값)으로 처리하지 않는다. 
			success : function(data){
				console.log(data)
				if(data !== null){
					displayUploaedfile(data);
				}
			},
			error : function(data){
			},
			
		})
		
	}
	
	// 삭제요청 ajax
	// ajax 통신
	function sendData2(url, type, data, dataType, async) {
		$.ajax({
			url : url || "", 
			type : type || "POST", 
			data : data || null, 
			dataType : dataType || "json", 
			async : async || false,
			success : function(data){
				
			},
			error : function(data){
			},
			
		})
		return true;
	}
	
	// 업로드한 파일을 출력
	function displayUploaedfile(data) {
		let output = "";
		
		$.each(data, function(i, elt){
			let name = elt.newFileName.replaceAll("\\", "/");
			
			if(elt.thumbFileName != null){ // 업로드한 파일이 이미지인 경우
				let thumb = elt.thumbFileName.replaceAll("\\", "/");
				output += `<div id="thumbnailObj\${i}" class="thumbnailObj">`; 
				output += `<img id="\${elt.originalFileName}" alt="" src="../resources/uploads/\${thumb}">`; // \${thumb} \는 el이 아니다라는 표시
				output += `<img class="rmBtn" alt="" src="../resources/img/remove.png" onclick="rmFile(this)">`;
				output += `</div>`;
				
			} else { // 업로드한 파일이 이미지가 아닌 경우
				output += `<a href="../resources/uploads/\${name}">\${elt.originalFileName}</a>`; // \${thumb} \는 el이 아니다라는 표시
			}
			
		})
		
		$(".uploadedFileArea").html(output);
	}
	
	function rmFile(ele){
// 		console.log(ele);
		let removeFile = $(ele).prev().attr("id"); // 타겟 아이디 가져오기
		console.log(removeFile)
		
		if(sendData2("removeFile", "GET", {"removeFile":removeFile}, "text")){
			$(ele).prev().parents().eq(0).remove();
		}
		
		
	}
	
	function cancel(){
		if(confirm("취소하시겠습니까?")){
			
			if(sendData2("removeAllFile", "GET")){
				$(".thumbnailObj").remove();				
			}
		}
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
	.rmBtn{
		width: 20px;
		
	}
</style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<!-- 로그인 하지 않은 유저는 login.jsp페이지로 이동시키기. -->
<c:if test="${sessionScope.loginMember == null }">
	<c:redirect url="../member/login"></c:redirect>
</c:if>

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
				<div class="uploadedFileArea"></div>
			</div>

			<button type="submit" class="btn btn-primary">글쓰기</button>
			<button type="reset" class="btn btn-danger" onclick="cancel()">취소</button>
		</form>
	</div>
	
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>