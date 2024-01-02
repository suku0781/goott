package com.ajaxjsp.controller;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOimpl;
import com.ajaxjsp.etc.ResponseToJson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/sortList.do"})
public class SetEmployeeSort extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("application/json; charset=utf-8");
    String target = request.getParameter("sortBy");
    PrintWriter out = response.getWriter();
    try
    {
      EmployeesDAO dao = EmployeesDAOimpl.getInstance();
      List lst = dao.selectAllEmp(target);

      ResponseToJson json = new ResponseToJson();
      out.print(json.empDataStrToJson(lst, "sortBy"));
    }
    catch (NamingException|SQLException e) {
      e.printStackTrace();
    }
    out.flush();
    out.close();
  }
}