package com.ajaxjsp.controller;

import com.ajaxjsp.dao.EmployeesDAO;
import com.ajaxjsp.dao.EmployeesDAOimpl;
import com.ajaxjsp.etc.ResponseToJson;
import com.ajaxjsp.exception.OutputJSONEorError;
import com.ajaxjsp.vo.Job;
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

@WebServlet({"/getAllJobs.do"})
public class GetAllJobServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    resp.setContentType("application/json; charset=utf-8");

    PrintWriter out = resp.getWriter();
    EmployeesDAO dao = EmployeesDAOimpl.getInstance();
    try
    {
      List lst = dao.selectAllJob();

      ResponseToJson json = new ResponseToJson();
      String outputJson = json.jobDataStrToJson(lst);
      out.print(outputJson);
      out.close();
      System.out.println(outputJson);
    }
    catch (NamingException|SQLException e) {
      new OutputJSONEorError(); out.print(OutputJSONEorError.outputJSON(e));
    }
  }

  private String toJsonwithJsonSimple(List<Job> lst)
  {
    JSONObject json = new JSONObject();
    json.put("status", "success");
    json.put("target", "getAllJob");
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy³â MM¿ù ddÀÏ HH:mm:ss");
    String outputDate = fmt.format(Calendar.getInstance().getTime());
    json.put("outputDate", outputDate);
    json.put("count", Integer.valueOf(lst.size()));

    if (lst.size() > 0) {
      JSONArray jobs = new JSONArray();

      for (Job j : lst) {
        JSONObject job = new JSONObject();

        job.put("JOB_ID", j.getJob_id());
        job.put("JOB_TITLE", j.getJob_title());
        job.put("MIN_SALARY", Integer.valueOf(j.getMin_salary()));
        job.put("MAX_SALARY", Integer.valueOf(j.getMax_salary()));

        jobs.add(job);
      }

      json.put("jobs", jobs);
    }
    return json.toString();
  }
}