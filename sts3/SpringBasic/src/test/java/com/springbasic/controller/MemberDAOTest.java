package com.springbasic.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springbasic.persistence.MemberDAO;
import com.springbasic.vo.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // /**/ = 파일이든 폴더든 다 찾아라.
public class MemberDAOTest {
	@Inject
	private MemberDAO dao;
	
	@Test
	public void getDateTimeTest() {
		System.out.println(dao.getDate());
	}
	
	@Test
	public void selectMemberByUserId() {
		System.out.println(dao.selectMemberByUserId("jkl123"));
	}
	
	@Test
	public void insertMember() {
//		dao.insertMember(new Member("newMem2", "1234", "newmem2@mem.mem", null, 1, 0, null, "n"));
	}
	
	@Test
	public void selectAllMember() {
		List<Member> lst = dao.selectAllMember();
		for(Member m : lst) {
			System.out.println("TEST! "+m.toString());	
		}
		
	}
	
}
