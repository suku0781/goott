package com.miniproject.exception;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 현재 클래스가 공통 예외처리를 할 클래스임을 명시
public class CommonException {
	
	@ExceptionHandler(IOException.class)
	public String IOException(Exception e, Model model) {
		// 특정 예외처리메서드 
		model.addAttribute("errorMessage", e.getMessage()); // 에러메시지 바인딩
		model.addAttribute("errorStactTrace", e.getStackTrace());
		
		return "commonError";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandling(Exception e, Model model) {
		// 모든 예외처리메서드 
		model.addAttribute("errorMessage", e.getMessage()); // 에러메시지 바인딩
		model.addAttribute("errorStackTrace", e.getStackTrace());	

		return "commonError";
	}
}
