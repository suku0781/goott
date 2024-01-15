package com.miniPrj.service.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.controller.BoardFactory;
import com.miniPrj.dao.BoardCRUD;
import com.miniPrj.dao.BoardDAO;
import com.miniPrj.etc.PagingInfo;
import com.miniPrj.service.BoardService;
import com.miniPrj.vo.Board;

public class GetEntireBoardService implements BoardService {
	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = BoardCRUD.getInstance();
		
		// 페이지에 대한 정보가 없으면 1페이지 출력. 있으면 해당 페이지 출력
		int pageNo = 1; 
		
		if(request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals("")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		
		System.out.println(pageNo+"페이지 전체 목록 가져오기.");
		
		System.out.println("searchType : " + request.getParameter("searchType"));
		System.out.println("searchWord : " + request.getParameter("searchWord"));
		
		try {
			PagingInfo pi = pagingProcess(pageNo);
			
			List<Board> lst = dao.selectAllBoard(pi);
			
			System.out.println("lst : "+lst);
			
			if(lst.size() == 0) {
				request.setAttribute("boardList", null);
			} else {
				request.setAttribute("boardList", lst);
				request.setAttribute("pagingInfo", pi);
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("listAll.jsp").forward(request, response);		
		
		return null;
	}

	// 페이지를 처리
	private PagingInfo pagingProcess(int pageNo) throws NamingException, SQLException {
		PagingInfo pi = new PagingInfo();
		BoardDAO dao = BoardCRUD.getInstance();
		
		pi.setPageNo(pageNo);
		
		// 전체 글의 갯수 가져오기
		pi.setTotalPostCnt(dao.getTotalPostCnt());
		
		// 총 페이지 수 
		pi.setTotalPageCnt(pi.getTotalPostCnt(), pi.getViewPostCntPerPage());

		// 보여주기 시작할 글 index번호
		pi.setStartRowIndex();
		
		// 전체 페이징 블럭 갯수 
		pi.setTotalPagingBlockCnt();
		
		// 현재 페이지가 속한 페이징 블럭 번호
		pi.setPageBlockOfCurrentPage();
		
		// 현재 페이징 블럭 시작 페이징 블럭 번호 
		pi.setStartNumOfCurrentPagingBlock();

		// 현재 페이징 블럭 끝 페이징 블럭 번호 
		pi.setEndNumOfCurrentPagingBlock();
		
		return pi;
	}



}