package com.test.service;

import java.io.IOException;

import javax.print.MultiDocPrintService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.mem")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet¿∏∑Œ »£√‚µ ");
		doService(request, response);
	}
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("command : " + command);
		System.out.println("request email : " + request.getParameter("email"));
		
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost∑Œ »£√‚µ ");
		doService(request, response);
	}

}
