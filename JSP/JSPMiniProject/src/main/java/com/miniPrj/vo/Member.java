package com.miniPrj.vo;

import java.sql.Date;

public class Member {
	private String userId;	
	private String userPw;		
	private String userEmail;
	private Date registDate;
	private int userImg;
	private int userPoint;
	private String memberImg;
	private String isAdmin;
	
	public Member(String userId, String userPw, String userEmail, Date registDate, int userImg, int userPoint) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userEmail = userEmail;
		this.registDate = registDate;
		this.userImg = userImg;
		this.userPoint = userPoint;
	}
	
	public Member(String userId, String userPw, String userEmail, Date registDate, int userImg, int userPoint, String memberImg, String isAdmin) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userEmail = userEmail;
		this.registDate = registDate;
		this.userImg = userImg;
		this.userPoint = userPoint;
		this.memberImg = memberImg;
		this.isAdmin = isAdmin;
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
	public int getUserImg() {
		return userImg;
	}
	public void setUserImg(int userImg) {
		this.userImg = userImg;
	}
	public int getUserPoint() {
		return userPoint;
	}
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}
	public String getMemberImg() {
		return memberImg;
	}
	public void setMemberImg(String memberImg) {
		this.memberImg = memberImg;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPw=" + userPw + ", userEmail=" + userEmail + ", registDate="
				+ registDate + ", userImg=" + userImg + ", userPoint=" + userPoint + ", memberImg=" + memberImg
				+ ", isAdmin=" + isAdmin + "]";
	}
	
	
	
	
}
