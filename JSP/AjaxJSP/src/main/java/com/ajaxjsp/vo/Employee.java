package com.ajaxjsp.vo;

import java.sql.Date;

public class Employee {
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private float salary;
	private float commision_pct;
	private int manager_id;
	private int department_id;
	private String deapartment_name;

	public Employee(int employee_id, String first_name, String last_name, String email, String phone_number,
			Date hire_date, String job_id, float salary, float commision_pct, int manager_id, int department_id,
			String deapartment_name) {
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
		this.hire_date = hire_date;
		this.job_id = job_id;
		this.salary = salary;
		this.commision_pct = commision_pct;
		this.manager_id = manager_id;
		this.department_id = department_id;
		this.deapartment_name = deapartment_name;
	}

	public int getEmployee_id() {
		return this.employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return this.first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return this.last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return this.phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getHire_date() {
		return this.hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public String getJob_id() {
		return this.job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public float getSalary() {
		return this.salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getCommision_pct() {
		return this.commision_pct;
	}

	public void setCommision_pct(float commision_pct) {
		this.commision_pct = commision_pct;
	}

	public int getManager_id() {
		return this.manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public int getDepartment_id() {
		return this.department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getDeapartment_name() {
		return this.deapartment_name;
	}

	public void setDeapartment_name(String deapartment_name) {
		this.deapartment_name = deapartment_name;
	}

	public String toString() {
		return "Employee [employee_id=" + this.employee_id + ", first_name=" + this.first_name + ", last_name="
				+ this.last_name + ", email=" + this.email + ", phone_number=" + this.phone_number + ", hire_date="
				+ this.hire_date + ", job_id=" + this.job_id + ", salary=" + this.salary + ", commision_pct="
				+ this.commision_pct + ", manager_id=" + this.manager_id + ", department_id=" + this.department_id
				+ ", deapartment_name=" + this.deapartment_name + "]";
	}
}