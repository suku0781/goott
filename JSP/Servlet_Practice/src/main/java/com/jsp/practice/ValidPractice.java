package com.jsp.practice;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.MemberDTO;

@WebServlet("/ValidPractice")
public class ValidPractice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ValidPractice() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String now = sdf.format(c.getTime());
		int hobbyCount = 1;
		int jobCount = 1;
		String hobbyStr = "";
		String jobStr = "";
		
		
		String id = request.getParameter("userId");
		String pw = request.getParameter("pwd");
		String pwRepeat = request.getParameter("psw-repeat");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		int age = 0;
		String phoneNumber = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");
		String[] hobby = request.getParameterValues("hobby");
		String[] job = request.getParameterValues("job");
		String memo = request.getParameter("memo");
		String agree = request.getParameter("agree");
		
		LocalDate nowDate = LocalDate.parse(now, DateTimeFormatter.ISO_DATE);
		LocalDate targetDate = LocalDate.parse(birthday, DateTimeFormatter.ISO_DATE);
		
		Period diff = Period.between(nowDate, targetDate);
		
		age = Math.abs(diff.getYears());
		
		System.out.println("아이디 : "+id);
		System.out.println("비밀번호 : "+pw);
		System.out.println("비밀번호확인 : "+pwRepeat);
		System.out.println("이메일 : "+email);
		System.out.println("생년월일 : "+birthday);
		System.out.println("나이 : "+age);
		System.out.println("휴대폰 : "+phoneNumber);
		System.out.println("성별 : "+gender);
		for(String h : hobby) {
			System.out.println("취미"+(hobbyCount++)+" : "+h);	
			hobbyStr += h + ", ";
		}
		for(String j : job) {
			System.out.println("직업"+(jobCount++)+" : "+j);	
			jobStr += j + ", ";
		}
		
		System.out.println("메모 : "+memo);
		System.out.println("동의여부 : "+agree);
		
		// Member객체를 Request바인딩
		MemberDTO member = new MemberDTO(id, pw, email, birthday, age, phoneNumber, gender, hobbyStr, jobStr, memo);
		request.setAttribute("member", member);
		RequestDispatcher rd = request.getRequestDispatcher("outputMember.jsp");	
		rd.forward(request, response);
		if(agree.isEmpty() && !agree.toLowerCase().equals("y")) {
			PrintWriter out = response.getWriter();
			out.println("<div>"
					+ "<p>동의항목을 체크하십시오.ㅣ</p>"
					+ "<a href='javascript:history.go(-1)'>동아가기</a>"
					+ "</div>");
			
			out.close();
		}
	}

}
