package com.jsp.basic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.*;

@WebServlet("/joinTest")
public class joinTest extends HttpServlet {
	private static final long serialVersionUID = -5597869918329276548L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String now = sdf.format(c.getTime());
		
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String email = req.getParameter("email");
		String birthday = req.getParameter("birthday");
		LocalDate nowDate = LocalDate.parse(now, DateTimeFormatter.ISO_DATE);
		LocalDate targetDate = LocalDate.parse(birthday, DateTimeFormatter.ISO_DATE);
		
		Period diff = Period.between(nowDate, targetDate);
		
		int age = Math.abs(diff.getYears());
		String phoneNumber = req.getParameter("phoneNumber");
		String gender = req.getParameter("gender");
		String[] hobby = req.getParameterValues("hobby");
		String[] job = req.getParameterValues("job");
		String memo = req.getParameter("memo");
		
		MemberDTO member = new MemberDTO(id, pw, email, birthday, age, phoneNumber, gender, hobby, job, memo);
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("joinTest2.jsp").forward(req, resp);;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
