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
    	System.out.println("init 메서드가 호출됨. " + this.initCount);
    	System.out.println("변경사항...");
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() : GET요청이 호출됨." + this.doGetCount++);
	}
	

	@Override
	public void destroy() {
		System.out.println("destroy() : destroy	요청이 호출됨." + this.doGetCount++);
	}

	
	

}
