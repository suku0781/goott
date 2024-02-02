package com.practice.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.practice.domain.Board;
import com.practice.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // /**/ = 파일이든 폴더든 검색하라.
public class boardDAOTest {
	@Inject
	private BoardDAO dao;
	
	@Test
	public void getAllBoardList() {
		System.out.println(dao.getAllBoard());
//		for(Board b : lst) {
//			System.out.println(b);
//		}
		
	}
}
