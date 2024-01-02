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
	private String[] hobby;
	private String[] job;
	private String memo;
	
	// 奄沙持失切
	public MemberDTO() {
		
	}
	
	// 持失切
	public MemberDTO(String id, String pw, String email, String birthday, int age, String phoneNumber, String gender, String[] hobby, String[] job, String memo){
		super();
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.birthday = birthday;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.hobby = hobby;
		this.job = job;
		this.memo = memo;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public String getJobstr() {
		return getJobstr();
	}

	public void setJobstr(String[] job) {
		this.job = job;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
