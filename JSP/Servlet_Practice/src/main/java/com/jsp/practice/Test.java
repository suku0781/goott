package com.jsp.practice;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class Test extends HttpServlet {
	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("test!!");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = req.getParameter("date");
		String text = req.getParameter("text");
		java.sql.Date d = java.sql.Date.valueOf(date);
		System.out.println();
		req.setAttribute("date", date);
		req.setAttribute("text", text);
		RequestDispatcher rd = req.getRequestDispatcher("./outputResult.jsp");
		
		rd.forward(req, resp);
		
		
	}

}
