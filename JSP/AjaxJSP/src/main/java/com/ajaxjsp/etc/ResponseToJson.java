package com.ajaxjsp.etc;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ajaxjsp.vo.Dept;
import com.ajaxjsp.vo.Employee;
import com.ajaxjsp.vo.Job;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ResponseToJson {
	public String empDataStrToJson(List<Employee> lst, String type) {
		JSONObject json = new JSONObject();

		json.put("status", "success");
		json.put("target", type);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
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

	public String deptDataStrToJson(List<Dept> lst) {
		JSONObject json = new JSONObject();

		json.put("status", "success");
		json.put("target", "getAllDepartment");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
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

	public String jobDataStrToJson(List<Job> lst) {
		JSONObject json = new JSONObject();
		json.put("status", "success");
		json.put("target", "getAllJob");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
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
	
	private String toJsonWithGson(List<Employee> lst) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<Employee>>() {}.getType();
		System.out.println("타입 : " + type.toString());
		
		String outputJson = gson.toJson(lst, type);
		
		return outputJson;
	}
}