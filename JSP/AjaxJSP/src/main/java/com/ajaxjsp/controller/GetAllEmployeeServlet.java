package com.ajaxjsp.controller;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOimpl;
import com.ajaxjsp.vo.Employee;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet({"/getAllEmployees.do"})
public class GetAllEmployeeServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("application/json; charset=utf-8");

    PrintWriter out = response.getWriter();
    EmployeesDAO dao = EmployeesDAOimpl.getInstance();
    try
    {
      List lst = dao.selectAllEmp();

      String outputJson = toJsonwithJsonSimple(lst);
      out.print(outputJson);
      out.close();
      System.out.println(outputJson);
    } catch (NamingException|SQLException e) {
      e.printStackTrace();
    }
  }

  private String toJsonwithJsonSimple(List<Employee> lst)
  {
    JSONObject json = new JSONObject();

    json.put("status", "success");
    json.put("target", "getAllEmployee");
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy³â MM¿ù ddÀÏ HH:mm:ss");
    String outputDate = fmt.format(Calendar.getInstance().getTime());
    json.put("outputDate", outputDate);
    json.put("count", Integer.valueOf(lst.size()));

    if (lst.size() > 0) {
      JSONArray employees = new JSONArray();

      for (Employee e : lst) {
        JSONObject employee = new JSONObject();

        employee.put("EMPLOYEE_ID", e.getEmployee_id());
        employee.put("FIRST_NAME", e.getFirst_name());
        employee.put("LAST_NAME", e.getLast_name());
        employee.put("EMAIL", e.getEmail());
        employee.put("PHONE_NUMBER", e.getPhone_number());

        Date tempDate = e.getHire_date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        employee.put("HIRE_DATE", sdf.format(tempDate));

        employee.put("JOB_ID", e.getJob_id());
        employee.put("SALARY", e.getSalary());
        employee.put("COMMISION_PCT", e.getCommision_pct());
        employee.put("MANAGER_ID", e.getManager_id());
        employee.put("DEPARTMENT_ID", e.getDepartment_id());
        employee.put("DEPARTMENT_NAME", e.getDeapartment_name());

        employees.add(employee);
      }

      json.put("employees", employees);
    }
    return json.toString();
  }
}