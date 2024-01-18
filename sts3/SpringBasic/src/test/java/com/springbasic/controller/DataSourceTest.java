package com.springbasic.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // /**/ = 파일이든 폴더든 다 찾아라.
public class DataSourceTest {
	
	@Inject
	private DataSource ds;

	@Test
	public void testConnection() {
		try(Connection con = ds.getConnection()){
			System.out.println("ds : " + con.toString());
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
