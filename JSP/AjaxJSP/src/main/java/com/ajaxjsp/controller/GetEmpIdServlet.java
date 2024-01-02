package com.ajaxjsp.controller;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOimpl;
import com.ajaxjsp.exception.OutputJSONEorError;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet({"/getEmpId.do"})
public class GetEmpIdServlet extends HttpServlet
{
  private static final long serialVersionUID = 8479241838038310333L;

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    resp.setContentType("application/json; charset=utf-8");

    PrintWriter out = resp.getWriter();
    EmployeesDAO dao = EmployeesDAOimpl.getInstance();
    try
    {
      int lastEmpId = dao.selectLastEmpId();
      String outputJSON = toJSONWithJSONSimple(lastEmpId);
      out.print(outputJSON);
      out.close();
    } catch (NamingException|SQLException e) {
      new OutputJSONEorError(); out.print(OutputJSONEorError.outputJSON(e));
    }
  }

  private String toJSONWithJSONSimple(int lastEmpId) {
    JSONObject json = new JSONObject();
    json.put("status", "success");
    json.put("target", "getEmpId");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy³â MM¿ù ddÀÏ HH:mm:ss");
    json.put("outputDate", sdf.format(Calendar.getInstance().getTime()));

    if (lastEmpId > -1) {
      json.put("lastEmpId", Integer.valueOf(lastEmpId));
    }

    return json.toString();
  }
}