package com.miniPrj.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.service.BoardService;
import com.miniPrj.service.MemberService;
 
@WebServlet("*.bo")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doService(request, response);
	}
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("요청한 URL" + request.getRequestURL());
		System.out.println("요청한 URI" + request.getRequestURI());
		System.out.println("요청한 통신방식" + request.getMethod());
		System.out.println("컨택스트 패스" + request.getContextPath());
		
		// 요청된 서블릿 매핑주소를 통해서 기능을 분류
		String reqURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		String command = reqURI.substring(contextPath.length());
		System.out.println("최종 요청된 서비스 : "+command);
		
		BoardFactory bf = BoardFactory.getInstance();
		BoardService service = bf.getService(command);
		
		if(service != null) {
			try {
				bf = service.doAction(request, response);
			} catch (ServletException | IOException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(bf!=null && bf.isRedirect()) {
			System.out.println("여기서 페이지 이동되는 부분 같은데");
			System.out.println(bf);
			response.sendRedirect(bf.getWhereToGo());
		}
		
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doService(request, response);
	}

}
