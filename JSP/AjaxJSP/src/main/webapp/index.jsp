<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.ko.min.js" integrity="sha512-L4qpL1ZotXZLLe8Oo0ZyHrj/SweV7CieswUODAAPN/tnqN3PA1P+4qPu5vIryNor6HQ5o22NujIcAZIfyVXwbQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
	let jobArr = [];
	let mngrArr = [];
	let deptArr = [];
	let updateTargetEmployeeData;
	
	// html이 로딩 완료될 때 실행되는 메서드
 	$(document).ready(function(){
 		init();
 		
 		// 직급 선택 시 급여 최대 최소 변경 
 		$("#selectJob").on("change", function(e){
 			if(e.target.value !== ""){
				makesalInput(e.target.value);
 			}
 		})
 		
 		$("#mngrId").on("change", function(e){
 			if(e.target.value !== ""){
				console.log(e.target.value)
				if(e.target.size > e.target.value){
					$("#mngrId").val(mngrArr[e.target.value]);		
				}
 			}
 		})
 		$("#addUser").on("click", function(){
 			console.log("유저 추가");
				$("#firstName").val("");
				$("#lastName").val("");
				$("#email").val("");
				$("#phone").val("");
				$("#hiredate").val("");
				$("#selectJob").val("");
				$("#salary").val("");
				$("#salaryNum").attr("");
				$("#salaryNum").attr("");
				$("#commission").val("");
				$("#mngrId").val("");
				$("#deptName").val("");
				$(".btn-primary").text("Regist");
				$(".modal-title").text("Regist New Employee");
 		})
 		
 		for(let i = 0 ; i < $(".usrMngrBtn").length ; i++) {
 			$(".usrMngrBtn")[i].addEventListener("click", function(e){
 	 			console.log("test" + e.target.id);
 	 			if(e.target.id == "addUser"){
 	 				console.log("유저 추가");
 	 				$("#firstName").val("");
 	 				$("#lastName").val("");
 	 				$("#email").val("");
 	 				$("#phone").val("");
 	 				$("#hiredate").val("");
 	 				$("#selectJob").val("");
 	 				$("#salary").val("");
 	 				$("#salaryNum").attr("");
 	 				$("#salaryNum").attr("");
 	 				$("#commission").val("");
 	 				$("#mngrId").val("");
 	 				$("#deptName").val("");
 	 				$(".btn-primary").text("Regist");
 	 				$(".modal-title").text("Regist New Employee");
 	 			} else if(e.target.id.indexOf("modifyUser") !== -1) {
 	 				console.log("유저 수정");
 	 				let empdata = JSON.parse(e.target.dataset.empdata);
 	 				let minSal = $("#selectJob").find("option:selected").attr('min');
 	 				let maxSal = $("#selectJob").find("option:selected").attr('max');
 	 				
 	 				$("#emplId").val(empdata.EMPLOYEE_ID);
 	 				$("#firstName").val(empdata.FIRST_NAME);
 	 				$("#lastName").val(empdata.LAST_NAME);
 	 				$("#email").val(empdata.EMAIL);
 	 				$("#phone").val(empdata.PHONE_NUMBER);
 	 				$("#hiredate").val(empdata.HIRE_DATE);
 	 				$("#selectJob").val(empdata.JOB_ID);
 	 				$("#salary").val(empdata.SALARY);
 	 				$("#salaryNum").attr("min", minSal);
 	 				$("#salaryNum").attr("max", maxSal);
 	 				$("#commission").val(empdata.COMMISION_PCT);
 	 				$("#mngrId").val(empdata.MANAGER_ID);
 	 				$("#deptName").val(empdata.DEPARTMENT_NAME);
 	 				$(".btn-primary").text("Edit");
 	 				$(".modal-title").text("Edit Employee");
 	 			}
 	 		})	
 		}
 		
 		$(".deleteUser").on("click", function(){
 			console.log("유저 삭제");
// 			let empdata = JSON.parse(e.target.dataset.empdata);
			
			$(".modal-title").text("Delete Employee");
			console.log($(this).parent().parent().children().html())
			$("#delTargetEmp").val($(this).parent().parent().children().html())
			
 		});
 		
 		$("#delBtn").on("click", function(){
 			let obj = {"empNo":$("#delTargetEmp").val()};
 			console.log($("#delTargetEmp").val()+"번 삭제")
 			getData("deleteEmployee.do", "POST", obj)
 		})
 		
 		
 		$("#selectSort").on("change", function(e){
			let obj = {};
			if($("#search").val() !== ""){ // 검색한 경우
				obj.sortBy = e.target[e.target.selectedIndex].id;
				obj.searchValue = $("#search").val();
				console.log("srch "+obj.sortBy + ", " + obj.searchValue);
				getData("searchEmployee.do", "POST", obj);
				
			} else { // 검색하지 않은 경우
				obj.sortBy = e.target[e.target.selectedIndex].id;
				console.log(obj.sortBy);
				getData("sortList.do", "POST", obj);
				
			}
			
		})
 	}); // end of $(document).ready()
 	
 	// 초기화 함수
 	function init(){
 		getData("getEmpId.do");
 		getData("getAllEmployees.do");
 		getData("getAllJobs.do", "POST" );
 		getData("getAllDepts.do", "POST" );
 		getDatepicker(); // datepicker() 라이브러리
 		
 	}
 	
	function autoHyphen(target) {
	 	target.value = target.value.replace(/[^0-9]/g, '').replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
	}
 	
 	// select 변경 시 실행 함수
 	function makesalInput(jobId){
 		let minSal = 0;
 		let maxSal = 0;
 		let avgSal = 0; // 평균값 
 		
 		// 선택된 job_id의 최소급여 최대급여 가져오기
 		$.each(jobArr, function(index, item){
 			if(jobId == item.JOB_ID){
 				minSal = item.MIN_SALARY;
 				maxSal = item.MAX_SALARY;
 				avgSal = (minSal + maxSal) / 2;
 			}
 		})
 		
 		$("#salaryNum").attr("min", minSal);
 		$("#salaryNum").attr("max", maxSal);
 		$("#salaryNum").val(minSal);
 		$("#salary").val(avgSal);
 		$("#spanSal").text(avgSal);
 		
 	}
 	
 	// DB로부터 데이터 읽기 메서드
 	function getData(url, type, data, dataType, async){
 		console.log("getData() 메서드 왔다." + data);
 		
 		$.ajax({
 	        url: url, // 서블릿 매핑주소 
 	        type: type || 'GET',
 	        dataType : dataType || 'json',
 	        async: async || false,
 	        data: data || null,
	        success : function (data){
 	        	console.log(data)
 	        	if(data.status == "fail"){
 	        		alert("데이터를 불러오지 못했습니다.");
 	        		return;
 	        	}
 	        	
	        	if(data.target == "getAllEmployee"){
	        		empData = data;
	 	        	outputEntireEmployees(data);
	        	} else if(data.target == "getAllJob"){
	        		outputJobsData(data);
	        	} else if(data.target == "getEmpId"){
	        		console.log("마지막사번코드가져오기!!" + $("#emplId").val( (data.lastEmpId) + 1 ) );
	        		
	        	} else if(data.target == "getAllDepartment"){
	        		outputDepartments(data);
	        	} else if(data.target == "setEmployee"){
					if(data.status == "success"){
						alert("등록됨.");
						
						// 모달창 닫기
						document.getElementById("empModal").classList.remove("show")
						document.getElementsByClassName("modal-backdrop")[0].classList.remove("show")
						
						init();
					} else {
						alert("등록되지 않음.");
						
					}
						
	        	} else if(data.target == "srchEmp"){
	        		console.log(data);
	        		outputSrchResult(data);
	        	} else if(data.target == "sortBy"){
	        		outputEntireEmployees(data);
	        	} else if(data.target == "updateEmployee"){
	        		if(data.status == "success"){
	        			alert("수정됨.");
	        			
	        			// 모달창 닫기
	        			document.getElementById("empModal").classList.remove("show");
	        			document.getElementsByClassName("modal-backdrop")[0].classList.remove("show");
	        			
	        			outputEntireEmployees(data);
	        		} else {
	        			alert("수정되지 않음.")
	        		}
	        	} else if(data.target == "deleteEmployee"){
	        		if(data.status == "success"){
	        			alert("삭제됨.");
	        			
	        			// 모달창 닫기
	        			document.getElementById("empModal").classList.remove("show");
	        			document.getElementsByClassName("modal-backdrop")[0].classList.remove("show");
	        			
	        			init();
// 	        			outputEntireEmployees(data);
	        		}
	        	}
 	        	 
 	        },
 	        error : function (data){
	        	console.log("error! "+data);
	        	
 	        }
 	      })
 	}
 	
 	// 직원명 검색 후 출력 메서드
 	function outputSrchResult(data){
 		outputEntireEmployees(data);
 		
 	}
 	
 	// 직급 가져오기 메서드
 	function outputJobsData(data){
 		if(data.success == "fail") {
 			alert("데이터를 불러오지 못했습니다.");
 			return false;
 		}
 		jobArr = data.jobs;
 		let output = `<option value="">직급 선택</option>`;
 		for(let job of data.jobs){
 			output+= '<option id="'+job.JOB_ID+'" value="'+job.JOB_ID+'" min="'+job.MIN_SALARY+'" max="'+job.MAX_SALARY+'">'+job.JOB_TITLE+'</option>';
 		}
 		$("#selectJob").html(output); 		
 	}
 	
 	// datepicker라이브러리 메서드
 	function getDatepicker(){
 		$('#hiredate').datepicker({
 	        format: "yyyy-mm-dd",
//  	        minViewMode: 1,
 	        language: "ko",
 	        autoclose: true
 	    })

 	    $('#get-history').on('click', function() {
 	        const dateArr = $('#hiredate').val().split('-')
 	        location.href = '/history?year=' + dateArr[0] + '&month=' + dateArr[1]
 	    })

 	}
 	
 	
	// 모든 직원 데이터 파싱하여 출력.
 	function outputEntireEmployees(json){
 		$("#outputCnt").html("<div>데이터 : " + json.count + "개 </div><div><img alt='add' src='img/add.png' id='addUser' class='usrMngrBtn' width='20' data-bs-toggle='modal' data-bs-target='#empModal'/></div>");
 		$("#outputData").html("출력일시 : " + json.outputDate);
 		
 		let output = `<table class="table">
 		    <thead>
 		      <tr>
 		        <th>사원번호</th>
 		        <th>이름</th>
 		        <th>이메일</th>
 		        <th>입사일</th>
 		        <th>직급</th>
 		        <th>급여</th>
 		        <th>커미션</th>
 		        <th>매니저</th>
 		        <th>부서명</th>
 		        <th></th>
 		      </tr>
 		    </thead>
 		    <tbody>`;
 		      
		$.each(json.employees, function(i, item){
			// 매니저 이름 찾기
			let managerName = "";
			$.each(json.employees, function(j, tem){
				if(item.MANAGER_ID == tem.EMPLOYEE_ID){
					managerName = item.LAST_NAME + ", " + item.FIRST_NAME;
				}
			})
			
			output += "<tr>";
			output += "<td>"+item.EMPLOYEE_ID+"</td>";
			output += "<td>"+item.LAST_NAME + " " + item.FIRST_NAME+"</td>";
			output += "<td>"+item.EMAIL+"</td>";
			output += "<td>"+item.HIRE_DATE+"</td>";
			output += "<td>"+item.JOB_ID+"</td>";
			output += "<td>"+item.SALARY+"</td>";
			output += ((item.COMMISION_PCT) * 100 !== 0) ? "<td>"+ item.COMMISION_PCT * 100+"%</td>" : "<td>-</td>";
			output += "<td>"+managerName+"</td>";
			output += "<td>"+item.DEPARTMENT_NAME+"</td>";
			output += "<td><img alt='modify' src='img/modify.png' id='modifyUser"+i+"' class='usrMngrBtn' width='15' data-bs-toggle='modal' data-bs-target='#empModal' data-empData='"+JSON.stringify(item).replaceAll(/"/g, '\"')+"'/></td>";
			output += "<td><img alt='delete' src='img/remove.png' id='deleteUser"+i+"' class='usrMngrBtn deleteUser' width='20' data-bs-toggle='modal' data-bs-target='#delModal' data-empData='"+JSON.stringify(item).replaceAll(/"/g, '\"')+"' /></td>";
			output += "</tr>";
			
			if(!mngrArr.includes(Number(item.MANAGER_ID))){
				mngrArr.push(Number(item.MANAGER_ID));
			}
		})
		output += `</tbody></table>`;
	  $(".empInfo").html(output);
 	}
	
	// 모든 직업 파싱하여 출력하는 메서드
	function outputDepartments(json){
		deptArr = json.departments;
 		let output = `<option value="">직급 선택</option>`;
 		for(let dept of json.departments){
 			output+= '<option id="'+dept.department_id+'" data-mgrId="'+dept.manager_id+'" data-locId="'+dept.location_id+'">'+dept.department_name+'</option>';
 		}
 		$("#deptName").html(output); 	
	}
	
	function getMgrSelection(){
		
	}
	
 	function regiEmp(el) {
 		let obj = {};
 		let arr = [];
 		let tmparr = [];
 		let tmparr2 = [];
 		let updateArr = [];
 		
 		if(el.textContent == 'Edit'){
 			let flag = false;
 			console.log("수정버튼 클릭됨.");
 			if(obj = validationCheck(el.textContent)){
 	 			obj.salary = $("#salary").val() || 0;
 	 			obj.empId = $("#emplId").val() || null;
 	 			obj.commission = $("#commission").val() || null;
 	 			obj.deptId = $("#deptName").find("option:selected").attr("id") || null;
 	 		} else {
 	 			console.log("error!");
 	 		}
 			
 			obj.empNo = $("#emplId").val();
 			obj.firstName = $("#firstName").val();
 			obj.lastName = $("#lastName").val();
 			obj.email = $("#email").val();
 			obj.phone = $("#phone").val();
 			obj.hiredate = $("#hiredate").val();
 			obj.selectJob = $("#selectJob").val();
 			obj.mngrId = $("#mngrId").val();
 			
 			if( updateTargetEmployeeData.FIRST_NAME !== obj.firstName ||
	 			updateTargetEmployeeData.LAST_NAME !== obj.lastName || 
	 			updateTargetEmployeeData.EMAIL !== obj.email || 
	 			updateTargetEmployeeData.PHONE_NUMBER !== obj.phone || 
	 			updateTargetEmployeeData.HIRE_DATE !== obj.hiredate || 
	 			updateTargetEmployeeData.JOB_ID !== obj.job || 
	 			updateTargetEmployeeData.SALARY !== obj.salary || 
	 			updateTargetEmployeeData.COMMISION_PCT !== obj.commission || 
	 			updateTargetEmployeeData.MANAGER_ID !== obj.mngrId || 
	 			updateTargetEmployeeData.DEPARTMENT_ID !== obj.deptId){
	 			if(updateTargetEmployeeData.FIRST_NAME == obj.firstName) updateArr.push({"FIRST_NAME" : obj.firstName});
	 			if(updateTargetEmployeeData.LAST_NAME == obj.lastName) updateArr.push({"LAST_NAME" : obj.lastName});
	 			if(updateTargetEmployeeData.EMAIL == obj.email) updateArr.push({"EMAIL" : obj.email});
	 			if(updateTargetEmployeeData.PHONE_NUMBER == obj.phone) updateArr.push({"PHONE_NUMBER" : obj.phone});
	 			if(updateTargetEmployeeData.HIRE_DATE == obj.hiredate) updateArr.push({"HIRE_DATE" : obj.hiredate});
	 			if(updateTargetEmployeeData.JOB_ID == obj.job) updateArr.push({"JOB_ID" : obj.job});
	 			if(updateTargetEmployeeData.SALARY == obj.salary) updateArr.push({"SALARY" : obj.salary});
	 			if(updateTargetEmployeeData.COMMISION_PCT == obj.commission) updateArr.push({"COMMISION_PCT" : obj.commission});
	 			if(updateTargetEmployeeData.MANAGER_ID == obj.mngrId) updateArr.push({"MANAGER_ID" : obj.mngrId});
	 			if(updateTargetEmployeeData.DEPARTMENT_ID == obj.deptId) updateArr.push({"DEPARTMENT_ID" : obj.deptId});
	 			
	 			flag = true;
 			} else {
 				alert("변경사항 없음.")
 				return;
 			}
			
 			if(flag) getData("editEmp.do", "POST", obj);
 			
 		} else if(el.textContent == 'Regist'){
 			console.log("등록버튼 클릭됨.");
 			let flag = false;
 			
 			if(obj = validationCheck(el.textContent)){
 	 			obj.salary = $("#salary").val() || 0;
 	 			obj.empId = $("#emplId").val() || null;
 	 			obj.commission = $("#commission").val() || null;
 	 			obj.mngrId = $("#mngrId").val() || null;
 	 			obj.deptId = $("#deptName").find("option:selected").attr("id") || null;
	 				
 	 			flag = true;
 	 		} else {
 	 			console.log("error!");
 	 		}
 			
 			if(flag) getData("registNewEmp.do", "POST", obj);
 		}
//  	}
 	
	 		getData("deleteEmployee.do", "POST", obj);
 	}
 	
 	function validationCheck(type){
 		let obj = {};
 		if(type == "Regist"){
 			if(nameValid() && emailValid() && phoneValid() && hiredateValid() && selectJobValid() ) return obj;	
 		} else if(type == "Edit"){
 			if(nameValid() && emailValid() && phoneValid() && hiredateValid() && selectJobValid() ) return obj;
 			
 		}
 		
 		
 		function nameValid(){
 			let firstName = $("#firstName").val();
 			let lastName = $("#lastName").val();
 			
//  			let pattern =  /^[가-힣|A-Z|a-z|]*$/; // 이름 정규식 
 			let pattern =  /^[가-힣a-zA-Z\s]+$/
 				
 			if(firstName !== "" && lastName !== ""){
 				$('#firstName').val($('#firstName').val().trim());
 				$('#lastName').val($('#lastName').val().trim());
 				  if(!pattern.test($('#firstName').val()) || !pattern.test($('#lastName').val())) {   
 					  alert('이름을 확인하세요.');   
 					  $('#firstName').focus();   
 					  return false;  
				  } else {   
					  obj.firstName = firstName;
					  obj.lastName = lastName;
					  return true;  
				  }
 			} else {
 				return false;
 			}
 			
 		}
 		
 		function emailValid(){
 			let email = $("#email").val();
 			
 			let regExp = 	/^([A-Za-z]|[0-9])+$/;	
 			
 			if(email !== ""){
	 			if(regExp.test(email)){ // 형식에 맞는 경우 true 리턴
	 				obj.email = email;
	 				return true;	
	 			} else{
	 				return false;
	 			}
 			} else {
 				return false;
 			}
 		}
 		
 		function phoneValid(){
 			let phone = document.getElementById("phone").value.replaceAll("-",".");
 			
 			let regExp = /^.?[0-9]{3,4}.?[0-9]{3,4}.?[0-9]{4}$/;
 		    
 			
 			if(phone !== ""){
 				if(regExp.test(phone)){
 					obj.phone = phone;
 					return true;
 				} else {
 					return false;
 				}
 			} else {
 				return false;
 			}
 		}
 		
 		function hiredateValid(){
//  			let hiredate = document.getElementById("hiredate").value.replaceAll("-","/").slice(2);
 			let hiredate = document.getElementById("hiredate").value;
 			if(hiredate !== ""){
 				obj.hiredate = hiredate;
 				return true;
 			} else {
 				return false;
 			}
 		}
 		
 		function selectJobValid(){
 			let selectJob = $("#selectJob").val();
 			if(selectJob !== ""){
 				obj.job = selectJob;
 				return true;
 			} else {
 				return false;
 			}
 		}
 		
 	}
 	
 	function searchEmployee(value){
 		console.log("search employee : " + value);
 		
 		if(value.length < 3){
 			alert("검색할 직원 명을 3글자 이상 입력하십시오.");
 			return;
 		}
 		
 		let obj = {};
 		obj.searchValue = value.toLowerCase().replaceAll(" ", "").trim();
 		obj.searchValue = value.toLowerCase().replaceAll(" ", "").trim();
 		getData("searchEmployee.do", "POST", obj, "json", "true");
 	}
	
</script>
<style type="text/css">
</style>
</head>
<body>
	<div class="container">
		<h1 onclick="getData('getAllEmployees.do'); $('#search').val(''); ">Employees with Ajax</h1>
		<div class="searchEmp">
			<input type="text" id="search" placeholder="input Employee" onkeyup="if( event.keyCode == 13 ){ searchEmployee(this.value) }">
		</div>
		<div id="outputDate"></div>
		<div id="outputCnt" style="display: flex;"></div>
		<div id="sortData">
			<select id="selectSort"><option id="empId" selected>사번순</option>
				<option id="hireDate">입사일순</option>
				<option id="sal">급여순</option></select>
		</div>
		<div class="empInfo"></div>
	</div>



	<!-- The Modal -->
	<form action="" class="modal fade" id="empModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title"></h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="input-group mb-3">
						<span class="input-group-text">사원번호</span> <input type="text" class="form-control" id="emplId" name="emplId" readonly>
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">이름</span> <input type="text" class="form-control" placeholder="First Name" id="firstName" name="firstName"> <input type="text" class="form-control" placeholder="Last Name" id="lastName" name="lastName">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">이메일</span> <input type="text" class="form-control" id="email" placeholder="Your Email"> <span class="input-group-text">@example.com</span>
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">휴대폰번호</span> <input type="text" class="form-control" id="phone" oninput="autoHyphen(this)">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">입사일</span> <input type="text" id="hiredate" class="form-control" aria-label="Search" aria-describedby="basic-addon2" value="" th:value="|${year}-${month}|">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">직급</span> <select class="form-select" name="selectJob" id="selectJob" aria-label="Select Your JobId"></select>
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">급여</span> <input type="number" class="form-control" id="salary" name="salary" value=""> <input type="range" class="form-range" min="0" max="10000" step="1" id="salaryNum" name="salaryNum" oninput="document.getElementById('salary').value=this.value;">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">커미션</span> <input type="number" class="form-control" name="empName" id="commission" min="0" max="10" step="0.01">

					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">매니저번호</span> <input type="number" class="form-control" name="mngrId" id="mngrId">
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">부서명</span> <select class="form-select" name="deptName" id="deptName" aria-label="Select Your Department"></select>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="regiEmp(this)">Register</button>
					<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</form>
	<form action="" class="modal fade" id="delModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title"></h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<input type="hidden" id="delTargetEmp" value=""/>
					<div>사원을 삭제하시겠습니까?</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="delBtn">Delete</button>
					<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</form>
</body>
</html>