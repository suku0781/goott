<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container">
		<h1>login.jsp</h1>
		
		<form action="login" method="POST">
			<div class="mb-3 mt-3">
				<label for="userId" class="form-label">ID</label> 
				<input type="text" class="form-control" id="userId" name="userId" placeholder="Input your ID" >
			</div>
			<div class="mb-3 mt-3">
				<label for="userPw" class="form-label">PW</label> 
				<input type="password" class="form-control" id="userPw" name="userPw" placeholder="Input your PassWord" >
			</div>
			<div>
				<input type="checkbox" class="form-check-input" id="autoLogin" name="autoLogin" >
				<label for="autoLogin" class="form-label" >자동 로그인</label> 
			</div>

			<button type="submit" class="btn btn-primary" onclick="">Submit</button>
			<button type="reset" class="btn btn-danger">Cancel</button>
		</form>
	</div>
		
	<jsp:include page="../footer.jsp"></jsp:include>	
</body>
<script type="text/javascript">
	let isuserIdValidChkYn = false;
	let isuserPWValidChkYn = false;
	let isEmailAuthYn = false;
	
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
				
				if(data.target == "userIdDuplChk"){
					if(data.isDuplicate == "true"){
						printErrMsg("userId", "Input ID is Unusable. Id is Duplicated", false);
						result = true;
					} else {
						printErrMsg("userId", "Input ID is Usable.", false);
					}
				} else if(data.target == "userEmailDuplChk"){
// 					debugger
					if(data.isDuplicate == "true"){
						printErrMsg("userEmail", "Input Email is Unusable. Email is Duplicated", false);
						result = true;
					} else {
						printErrMsg("userEmail", "Input Email is Usable.", false);
					}
				} else if(data.target == "authCode"){
					console.log("인증코드 발송됨.");
					
					if(data.status == "success"){
						console.log("authCode : "+data.authCode)
					} else {
						
					}
				} else if(data.target == "checkAuthCode"){
					console.log("인증코드 확인됨.");
					
					if(data.activation == 'success'){
						$(".codeDiv").hide(2000);
						$("#sendCode").text("Confirmed").attr("disabled", true);
						printErrMsg("userEmail", "Confirmed.", false);
						isEmailAuthYn = true;
					} else {
						
					}
				}
			},
			fail: function(data){
				
			}
		})
		return result;
	}

	$(function(){
// 		if(location.search.slice(1) == "fail"){
// 			alert("정확한 아이디 또는 비밀번호를 입력하십시오.");
			
// 		}
		let loginStatus = '${status}'; // addFlashAttribute
		if(loginStatus == 'fail'){
			alert("정확한 아이디 또는 비밀번호를 입력하십시오.");
		}
		
		// 아이디 작성을 마쳤을 때 이벤트
		$("#userId").blur(function(){
			validUserId();
		});
		
		// 비밀번호 확인 작성을 마쳤을 때 이벤트
		$("#userPwRepeat").blur(function(){
			validUserPW();
		});
		
		// 이메일 작성을 마쳤을 때 이벤트
		$("#userEmail").blur(function(){
			validUserEmail();
		});
		
		// 이메일 인증버튼 클릭시 이벤트
		$(".sendCode").click(function(){
			if(validUserEmail("sendCode")){
				let userEmail = $("#userEmail").val();
				let obj = {};
				
				obj.userEmail = userEmail;
				
				getData("sendAuthMail", "GET", obj, "json", false);
				
				$(".codeDiv").show();
			}
		});
		
		// 코드확인 버튼 클릭 시 
		$(".confirmCode").click(function(){
			let userEmailAuthInp = $("#userEmailAuthInp").val();
			let obj = {};
			
			obj.userEmailAuthInp = userEmailAuthInp;
			getData("confirmCode", "GET", obj, "json", false);
		});
		
	}) // end of doc

	// 아이디 유효성검사 : 3자 이상 ~ 8자 이하 영문 + 숫자조합 
	function validUserId(){
		let userId = $("#userId").val();
		if(userId.length > 2 && userId.length < 9){
			// 아이디 중복검사
			let obj = {};
			obj.userId = userId;
			isuserIdValidChkYn = getData("dulpUserId.mem", "GET", obj, "json") ? false : true; // 아이디 중복검사 해서 중복이 아니면 true를 리턴하기위해서 반대로 설정함.
			return isuserIdValidChkYn;
		} else {
			printErrMsg("userId", "아이디는 3자 이상 ~ 8자 이하 영문 + 숫자조합으로 입력하십시오.", false); // function printErrMsg(id, msg, isFocus)
			return false;
		}
		
	}
	
	// 비밀번호 유효성검사 : '영문 + 숫자 + 특수문자 조합으로 6~20자리를 사용해야 합니다.
	function validUserPW(){
		let isValid = false;
		let userPw = $("#userPw").val();
		let userPwRepeat = $("#userPwRepeat").val();
		
		if(userPw.length > 5 && userPw.length < 21){
			// 비밀번호 유효성 검사
			let pattern = /^[A-Za-z0-9`~!@#\$%\^&\*\(\)\{\}\[\]\-_=\+\\|;:'"<>,\./\?]{6,20}$/;
			if(pattern.test(userPw)){
				if(userPw == userPwRepeat){
					printErrMsg("userPwRepeat",'비밀번호가 일치함.', false);
					isValid = true;
					isuserPWValidChkYn = true;
				} else {
					printErrMsg("userPwRepeat",'비밀번호가 일치하지않음.', false);
				}
			} else {
				printErrMsg("userPwRepeat",'비밀번호는 6자에서 20자 사이의 영문+숫자+특수문자 조합으로 입력해 주시기 바랍니다.', false);
			}
		} else {
			printErrMsg("userPwRepeat",'비밀번호는 6자에서 20자 사이의 영문+숫자+특수문자 조합으로 입력해 주시기 바랍니다.', false);
		}
		
		return isValid;
	}
	
	function validUserEmail(type){
		let userEmail = $("#userEmail").val();
		
		var pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		if(pattern.test(userEmail)){
			let obj = {};
			obj.userEmail = userEmail;
			
// 			return type == "sendCode" ? true : getData("dulpUserEmail.mem", "GET", obj, "json");
			return true;
		} else {
			printErrMsg("userEmail",'유효한 이메일을 입력하십시오.', false);
			return false;
		}
		
	}

	// 회원가입 유효성 검사
	function validation(){
		let isValid = false;
		let isAgree = $("#agree").prop("checked");
		let  = false;
		if(isuserIdValidChkYn && isuserPWValidChkYn && isEmailAuthYn){
			if(isAgree){
				isValid = true;
			} else {
				printErrMsg("agree",'이용 약관을 확인해 주시기 바랍니다.', false);
				isValid = false;
			}
		} else {
			isValid = false;
		}
		
		return isValid;
	}
	
	// 에러메시지 출력
	function printErrMsg(id, msg, isFocus){
		let errMsg = `<div class="errMsg">\${msg}</div>`;
		$(errMsg).insertAfter($(`#\${id}`)).parent();
		if(isFocus) $(`#\${id}`).focus() || $(`.\${id}`).focus();
		
		$('.errMsg').hide(2000);
	}
</script>
<style>
	.errMsg{
		color: red;
		font-size: 14px;
		font-weight: bold;
	}
</style>
</html>