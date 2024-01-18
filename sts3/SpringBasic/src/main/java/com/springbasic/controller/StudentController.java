package com.springbasic.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbasic.vo.Student;

@Controller
@RequestMapping("/student/*") // /student/*의 모든 url에 대해서 mapping
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@RequestMapping("outputStudent")
	public void outputStudent(Model model) {
		logger.info("outputStudent 호출됨.");
		
		Student stu = new Student("1601050", "김수혁");
		model.addAttribute("student", stu);
	}
	
	@RequestMapping(value = "inputStudent", method=RequestMethod.GET)
	public void inputStudentGET() {
		logger.info("inputStudentGET 호출됨.");
		
	}
	
//	@RequestMapping(value = "saveStudent", method = RequestMethod.POST)
//	public void inputStudentPOST(	@RequestParam("stuNo") String stuNo, 
//									@RequestParam("stuName") String stuName, 
//									Model model	) {
//		logger.info("saveStudent 호출됨.");
//		logger.info("학번 : " + stuNo);
//		logger.info("이름 : " + stuName);
//		
//		Student stu = new Student(stuNo, stuName);
//		
//		model.addAttribute("student", stu);
//	}
	
//	@RequestMapping(value = "saveStudent", method = RequestMethod.POST)
//	public void inputStudentPOST(Student stu, Model model) {
//		logger.info("saveStudent 호출됨.");
//		
//		model.addAttribute("inputStudent", stu);
//	} // 기본값으로 forward하는 방식.
	
	// redirect 하는 방식
	// redirectAttributes : redirect할 때 쿼리스트링으로 어떠한 값을 넘기고자 할 때 사용하는 객체 
	@RequestMapping(value = "saveStudent", method = RequestMethod.POST)
	public String inputStudentPOST(	Student stu, 
									Model model, 
									RedirectAttributes rttr) {
		logger.info("saveStudent 호출됨.");
		
		Map<String, String> map = new HashMap<>();
		map.put("no", stu.getStuNo());
		map.put("name", stu.getStuName());
		rttr.addAttribute("status", "success");
		rttr.addAllAttributes(map);
		rttr.addFlashAttribute("flashStatus", "flashStatusTEST"); // 휘발적인 
		model.addAttribute("studentNo", stu.getStuNo());
		model.addAttribute("studentName", stu.getStuName());
		return "redirect:homeStudent";
	} 
	
	@RequestMapping(value = "homeStudent" )
	public void homeStudent(@ModelAttribute(name="status") String status, 
							@RequestParam("status") String status2,
							@RequestParam Map<String, String> map, 
							@ModelAttribute("flashStatus") String flashStatus) { // void로 해놓을 경우 homeStudent를 찾아서 리턴함.
		logger.info("homeStudent GET방식으로 호출됨.");
		logger.info(status);
		logger.info(status2);
		logger.info(flashStatus);
//		logger.info(map);
	}
	
	
	
	// jackson databind 라이브러리 추가 json으로 response
	@RequestMapping(value="output")
	public @ResponseBody Student sampleStudent() {
		logger.info("sampleStudent 호출됨.");
		Student tmp = new Student("1601050", "김수혁");
		
		return tmp;
		
	}
	
}
