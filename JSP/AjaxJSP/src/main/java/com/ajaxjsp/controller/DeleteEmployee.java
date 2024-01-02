package com.ajaxjsp.controller;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOimpl;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/deleteEmployee.do"})
public class DeleteEmployee extends HttpServlet
{
  private static final long serialVersionUID = -8376161298865359995L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("application/json; charset=utf-8");

    int empNo = Integer.parseInt(request.getParameter("empNo"));
    System.out.println("empNo : " + empNo);

    EmployeesDAO dao = EmployeesDAOimpl.getInstance();
    try {
      int result = dao.deleteEmp(empNo);
      System.out.println("result : " + result);
      if (result == 1)
        System.out.println("삭제 성공");
      else
        System.out.println("삭제 실패");
    }
    catch (SQLException|NamingException e)
    {
      e.printStackTrace();
    }
  }
}