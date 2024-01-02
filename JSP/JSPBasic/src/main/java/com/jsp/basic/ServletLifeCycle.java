package com.jsp.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletLifeCycle")
public class ServletLifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int initCount = 1;
	int doGetCount = 1;
	int destroyCount = 1;
	
	
       
    public ServletLifeCycle() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	System.out.println(this.getClass().getName());
    	System.out.println("init �޼��尡 ȣ���. " + this.initCount);
    	System.out.println("�������...");
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() : GET��û�� ȣ���." + this.doGetCount++);
	}
	

	@Override
	public void destroy() {
		System.out.println("destroy() : destroy	��û�� ȣ���." + this.doGetCount++);
	}

	
	

}
