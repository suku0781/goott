package com.jsp.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MemberDTO {
	private String id;
	private String pw;
	private String email;
	private String birthday;
	private int age;
	private String phoneNumber;
	private String gender;
	private String hobbystr;
	private String jobstr;
	private String memo;
	
	// 생성자
	public MemberDTO(String id, String pw, String email, String birthday, int age, String phoneNumber, String gender, String hobbystr, String jobstr, String memo){
		super();
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.birthday = birthday;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.hobbystr = hobbystr;
		this.jobstr = jobstr;
		this.memo = memo;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobbystr() {
		return hobbystr;
	}

	public void setHobbystr(String hobbystr) {
		this.hobbystr = hobbystr;
	}

	public String getJobstr() {
		return jobstr;
	}

	public void setJobstr(String jobstr) {
		this.jobstr = jobstr;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getId() {
		return id;
	}

	public String getBirthday() {
		return birthday;
	}

	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
