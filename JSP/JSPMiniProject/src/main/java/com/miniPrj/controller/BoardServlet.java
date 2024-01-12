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
		
		System.out.println("��û�� URL" + request.getRequestURL());
		System.out.println("��û�� URI" + request.getRequestURI());
		System.out.println("��û�� ��Ź��" + request.getMethod());
		System.out.println("���ý�Ʈ �н�" + request.getContextPath());
		
		// ��û�� ���� �����ּҸ� ���ؼ� ����� �з�
		String reqURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		String command = reqURI.substring(contextPath.length());
		System.out.println("���� ��û�� ���� : "+command);
		
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
			System.out.println("���⼭ ������ �̵��Ǵ� �κ� ������");
			System.out.println(bf);
			response.sendRedirect(bf.getWhereToGo());
		}
		
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doService(request, response);
	}

}
