package com.ajaxjsp.exception;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.simple.JSONObject;

public class OutputJSONEorError
{
  public static String outputJSON(Exception e)
  {
    JSONObject json = new JSONObject();
    json.put("status", "fail");
    json.put("errorMessage", e.getMessage());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy³â MM¿ù ddÀÏ hh:mm:ss");
    json.put("outputDate", sdf.format(Calendar.getInstance().getTime()));
    return json.toString();
  }
}