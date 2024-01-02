package com.ajaxjsp.controller;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOimpl;
import com.ajaxjsp.exception.OutputJSONEorError;
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

@WebServlet({"/editEmp.do"})
public class SetEmployee extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("application/json; charset=utf-8");

    int empId = Integer.parseInt(request.getParameter("empId"));
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    Date hiredate = Date.valueOf(request.getParameter("hiredate"));
    String job = request.getParameter("job");
    float salary = Float.parseFloat(request.getParameter("salary"));
    float commission = Float.parseFloat(request.getParameter("commission"));
    int mngrId = Integer.parseInt(request.getParameter("mngrId"));
    int deptId = Integer.parseInt(request.getParameter("deptId"));

    PrintWriter out = response.getWriter();

    System.out.println("test!");

    Employee emp = new Employee(empId, firstName, lastName, email, phone, hiredate, job, salary, commission, mngrId, deptId, null);

    System.out.println(emp.toString());

    EmployeesDAO dao = EmployeesDAOimpl.getInstance();
    try {
      int result = dao.updateEmployee(emp);
      JSONObject json = new JSONObject();
      if (result == 1) {
        json.put("status", "success");
        json.put("target", "updateEmployee");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
        String outputDate = fmt.format(Calendar.getInstance().getTime());
        json.put("outputDate", outputDate);
        out.print(json.toJSONString());
      } else {
        json.put("status", "fail");
        json.put("target", "updateEmployee");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
        String outputDate = fmt.format(Calendar.getInstance().getTime());
        json.put("outputDate", outputDate);
        out.print(json.toJSONString());
      }
    }
    catch (NamingException|SQLException e) {
      e.printStackTrace();
    }
  }

  private void getEmployee(HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    int empNo = Integer.parseInt(request.getParameter("empNo"));
    System.out.println("사원 수정 서블릿 empNo : " + empNo);

    EmployeesDAO dao = EmployeesDAOimpl.getInstance();
    PrintWriter out = response.getWriter();
    try {
      Employee e = dao.selectEmployeeByEmpNo(empNo);
      if (e != null) {
        JSONObject json = new JSONObject();

        json.put("status", "success");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        String outputDate = sdf.format(Calendar.getInstance().getTime());
        json.put("outputDate", outputDate);

        JSONObject employee = new JSONObject();
        employee.put("EMPLOYEE_ID", Integer.valueOf(e.getEmployee_id()));
        employee.put("FIRST_NAME", e.getFirst_name());
        employee.put("LAST_NAME", e.getLast_name());
        employee.put("EMAIL", e.getEmail());
        employee.put("PHONE_NUMBER", e.getPhone_number());
        employee.put("HIRE_DATE", sdf.format(e.getHire_date()));
        employee.put("JOB_ID", e.getJob_id());
        employee.put("SALARY", Float.valueOf(e.getSalary()));
        employee.put("COMMISSION_PCT", e.getCommision_pct());
        employee.put("MANAGER_ID", Integer.valueOf(e.getManager_id()));
        employee.put("DEPARTMENT_ID", Integer.valueOf(e.getDepartment_id()));
        employee.put("DEPARTMENT_NAME", e.getDeapartment_name());

        json.put("employee", employee);
        out.print(json.toJSONString());
      }

    }
    catch (NamingException|SQLException e)
    {
      e.printStackTrace();
      out.print(OutputJSONEorError.outputJSON(e));
    }

    out.flush();
    out.close();
  }
}