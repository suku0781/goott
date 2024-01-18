package com.springbasic.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;


public class ConnectionTest {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/shk?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";
	
//	@Test // 아래의 메서드가 JUNIT4 라이브러리에 의해서 동작되는 test메서드임을 알리는 어노테이션
	public void testConnection() throws ClassNotFoundException {
		Class.forName(DRIVER);
		
		try( Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD) ){
			if(con != null) {
				System.out.println(con.toString()); // 예외가 발생하지 않으면
			}
		} catch(SQLException e) { // 예외가 발생하면 
			e.getStackTrace();
		}
	}
	
}
