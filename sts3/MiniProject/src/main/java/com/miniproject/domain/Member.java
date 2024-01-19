package com.miniproject.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Member {
	private String userId;
	private String userPw;
	private String userEmail;
	private Date registDate;
	private int userImg;
	private int userPoint;
	private String memberImg;
	private String isAdmin;
	
	
}
