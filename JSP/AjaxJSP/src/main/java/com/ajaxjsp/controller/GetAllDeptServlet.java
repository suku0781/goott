package com.ajaxjsp.controller;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOimpl;
import com.ajaxjsp.etc.ResponseToJson;
import com.ajaxjsp.vo.Dept;
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

@WebServlet({"/getAllDepts.do"})
public class GetAllDeptServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("application/json; charset=utf-8");

    PrintWriter out = response.getWriter();
    EmployeesDAO dao = EmployeesDAOimpl.getInstance();
    try
    {
      List lst = dao.selectAllDept();

      ResponseToJson json = new ResponseToJson();
      String outputJson = json.deptDataStrToJson(lst);
      out.print(outputJson);
      out.close();
      System.out.println(outputJson);
    } catch (NamingException|SQLException e) {
      e.printStackTrace();
    }
  }

  private String toJsonwithJsonSimple(List<Dept> lst)
  {
    JSONObject json = new JSONObject();

    json.put("status", "success");
    json.put("target", "getAllDepartment");
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy³â MM¿ù ddÀÏ HH:mm:ss");
    String outputDate = fmt.format(Calendar.getInstance().getTime());
    json.put("outputDate", outputDate);
    json.put("count", Integer.valueOf(lst.size()));

    if (lst.size() > 0) {
      JSONArray departments = new JSONArray();

      for (Dept d : lst) {
        JSONObject department = new JSONObject();

        department.put("department_id", Integer.valueOf(d.getDepartment_id()));
        department.put("department_name", d.getDepartment_name());
        department.put("manager_id", Integer.valueOf(d.getManager_id()));
        department.put("location_id", Integer.valueOf(d.getLocation_id()));

        departments.add(department);
      }

      json.put("departments", departments);
    }
    return json.toString();
  }
}