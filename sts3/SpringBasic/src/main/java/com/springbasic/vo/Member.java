package com.springbasic.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
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
