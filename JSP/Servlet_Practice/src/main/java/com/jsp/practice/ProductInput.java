package com.jsp.practice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.ProductDTO;

@WebServlet("/inputProd.do")
public class ProductInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ProductInput(){
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		
		String prodName = req.getParameter("prodName");
		int prodCnt = Integer.parseInt(req.getParameter("prodCnt"));
		int prodPrice = Integer.parseInt(req.getParameter("prodPrice"));
		String prodOption = req.getParameter("prodOption");
		
		System.out.println("여까지옴"+prodName);
		ProductDTO product = new ProductDTO(prodName, prodCnt, prodPrice, prodOption);
		req.setAttribute("product", product);
		System.out.println("여까지옴3"+product);
		RequestDispatcher rd = req.getRequestDispatcher("outputProduct.jsp");
		System.out.println("여까지옴4"+rd);
		rd.forward(req, resp);
		
	}

	
}
