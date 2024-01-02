package com.ajaxjsp.controller;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOimpl;
import com.ajaxjsp.vo.Employee;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

@WebServlet({"/registNewEmp.do"})
public class RegistNewEmp extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("application/json; charset=utf-8");

    System.out.println("POST방식으로 새로운 직원 등록 메서드 호출함.");

    int empId = Integer.parseInt(request.getParameter("empId"));
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    Date hiredate = Date.valueOf(request.getParameter("hiredate"));
    String job = request.getParameter("job");
    float salary = Float.parseFloat(request.getParameter("salary"));
    float commissionPCT = Float.parseFloat(request.getParameter("commission"));
    int managerId = Integer.parseInt(request.getParameter("mngrId"));
    int departmentId = Integer.parseInt(request.getParameter("deptId"));

    System.out.println(empId + firstName + lastName + email + phone + job + salary + commissionPCT + managerId + departmentId);
    System.out.println(hiredate);

    Employee emp = new Employee(0, firstName, lastName, email, phone, hiredate, job, salary, commissionPCT, managerId, departmentId, null);
    System.out.println(emp.toString());

    EmployeesDAO dao = EmployeesDAOimpl.getInstance();
    PrintWriter out = response.getWriter();
    try
    {
      String result = dao.insertEmp(emp);

      if (result.equals("SUCCESS")) {
        JSONObject json = new JSONObject();
        json.put("status", "success");
        json.put("target", "setEmployee");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
        json.put("outputDate", sdf.format(Calendar.getInstance().getTime()));
        out.print(json.toString());
      } else if (result.equals("ERROR")) {
        JSONObject json = new JSONObject();
        json.put("status", "fail");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
        json.put("outputDate", sdf.format(Calendar.getInstance().getTime()));
        out.print(json.toString());
      }
    }
    catch (NamingException|SQLException e) {
      e.printStackTrace();
    }
  }
}