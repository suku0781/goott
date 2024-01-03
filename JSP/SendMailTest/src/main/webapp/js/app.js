$(function(){
	
	// 이메일 인증 버튼 클릭 시 이벤트
	$("#getAuthCode").click(function(){
		if($("#email").val() !== ""){
			$("#emailAuthDiv").show();
			let obj = {};
			
			obj.email = $("#emailCodeInp").val();
			getData("sendAuthCode.mem", "post", obj);
		} else {
			alert("이메일을 입력하십시오.");
		}
	});
})


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
					if(data.isDuplicatee == "true"){
						printErrMsg("userId", "Input ID is Unusable. Id is Duplicated", true);
						result = true;
					} else {
						printErrMsg("userId", "Input ID is Usable.", false);
					}
				} else if(data.target == "authCode"){
					console.log("인증코드 발송됨.");
					
					if(data.status == "success"){
						
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