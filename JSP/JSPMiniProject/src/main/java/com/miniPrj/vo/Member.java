package com.miniPrj.vo;

import java.sql.Date;

public class Member {
	private String userId;	
	private String userPw;		
	private String userEmail;
	private Date registDate;
	private String userImg;
	private int userPoint;
	
	public Member(String userId, String userPw, String userEmail, Date registDate, String userImg, int userPoint) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userEmail = userEmail;
		this.registDate = registDate;
		this.userImg = userImg;
		this.userPoint = userPoint;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPw=" + userPw + ", userEmail=" + userEmail + ", registDate=" + registDate + ", userImg=" + userImg + ", userPoint=" + userPoint + "]";
	}
	
	
}
