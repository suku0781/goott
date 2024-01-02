package com.ajaxjsp.controller;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOimpl;
import com.ajaxjsp.etc.ResponseToJson;
import com.ajaxjsp.vo.Employee;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet({"/searchEmployee.do"})
public class GetEmployee extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String searchValue = request.getParameter("searchValue");
    String sortBy = request.getParameter("sortBy");

    EmployeesDAO dao = EmployeesDAOimpl.getInstance();
    PrintWriter out = response.getWriter();
    try {
      List lst = dao.searchEmp(searchValue, sortBy);
      ResponseToJson json = new ResponseToJson();
      String stringToJson = json.empDataStrToJson(lst, "srchEmp");
      System.out.println(lst);
      out.print(stringToJson);
      out.close();
    }
    catch (NamingException|SQLException e) {
      e.printStackTrace();
    }
  }

  private String makeStrToJson(List<Employee> lst) {
    JSONObject json = new JSONObject();
    JSONArray arr = new JSONArray();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy³â MM¿ù ddÀÏ hh:mm:ss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    json.put("status", "success");
    json.put("target", "srchEmp");
    json.put("outputDate", sdf.format(Calendar.getInstance().getTime()));
    json.put("count", Integer.valueOf(lst.size()));

    if (lst.size() > 0) {
      JSONObject employee = new JSONObject();
      for (Employee e : lst) {
        employee.put("EMPLOYEE_ID", Integer.valueOf(e.getEmployee_id()));
        employee.put("FIRST_NAME", e.getFirst_name());
        employee.put("LAST_NAME", e.getLast_name());
        employee.put("EMAIL", e.getEmail());
        employee.put("PHONE_NUMBER", e.getPhone_number());
        employee.put("HIRE_DATE", sdf2.format(e.getHire_date()));
        employee.put("JOB_ID", e.getJob_id());
        employee.put("SALARY", Float.valueOf(e.getSalary()));
        employee.put("COMMISSION_PCT", e.getCommision_pct());
        employee.put("MANAGER_ID", Integer.valueOf(e.getManager_id()));
        employee.put("DEPARTMENT_ID", Integer.valueOf(e.getDepartment_id()));
        employee.put("DEPARTMENT_NAME", e.getDeapartment_name());

        arr.add(employee);
      }
      json.put("employees", arr);
    }

    return json.toString();
  }
}