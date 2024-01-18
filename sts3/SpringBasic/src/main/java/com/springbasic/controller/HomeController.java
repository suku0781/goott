package com.springbasic.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springbasic.vo.Student;

@Controller // 현재 클래스가 컨트롤러 단임을 명시
public class HomeController {
	// logger : 로그를 남길 수 있도록 하는 객체
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// "/"가 GET방식으로 요청되면 아래의 home메서드를 호출한다. 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate ); // 바인딩
		
		return "home"; // viewResolver에게 "home" 문자열을 반환
		// "/WEB-INF/views/" + home + ".jsp" 경로에있는 jsp가 객체화 되어 최종 DispatcherServlet에 의해서 response됨.
	}
	
	@RequestMapping(value = "/doAct1")
	public String action1() {
		System.out.println("action1이 호출됨.");
		return "doAction1"; // /WEB-INF/views/doAction1.jsp 
	}
	
	@RequestMapping(value = "/doAct2")
	public void action2() {
		System.out.println("action2이 호출됨.");
		logger.info("doAction2");
//		return "doAction2"; // /WEB-INF/views/doAction1.jsp 
	}
	
	@RequestMapping(value = "doAct3", method = RequestMethod.GET)
	public String doAction3(Model model) {
		
		logger.info("doAction3 호출됨.");
		
		String name = "shk";
		model.addAttribute("name", name);
		
		return "doAction3";
	}
	
	@RequestMapping(value = "doAct4")
	public ModelAndView doAction4() {
		logger.info("doAction4 호출됨.");
		
		String name = "shk";
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", name);
		mav.setViewName("doAction4");
		
		return mav;
	}
	
	@RequestMapping(value = "/doAct5")
	public void doAction5(Model model) {
		logger.info("doAction5 호출됨.");
		Student stu = new Student("1601050", "김수혁");
//		model.addAttribute("student", stu);
		model.addAttribute(stu); 
		// 바인딩 객체의 이름을 지정하지 않은 경우 객체 앞글자를 소문자로 변환하여 바인딩한다. 
	}
}
