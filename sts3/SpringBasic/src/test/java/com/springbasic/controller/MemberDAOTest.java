package com.springbasic.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springbasic.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // /**/ = 파일이든 폴더든 다 찾아라.
public class MemberDAOTest {
	@Inject
	private MemberDAO dao;
	
	@Test
	public void getDateTimeTest() {
		System.out.println(dao.getDate());
	}
}
