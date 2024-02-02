package com.miniproject.domain;

import java.sql.Date;

import lombok.Data;

/**
 * @package : com.miniproject.domain
 * @fileName : Member.java
 * @author : shk
 * @update Date  : 2024. 2. 2. 
 * @description : 
 * @note : 20240202 자동로그인 기능 추가를 위한 컬럼 추가
 */
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
	
	private Date sessionLimit;
	private String sessionKey;
	
}
