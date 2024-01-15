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
		
		// �������� ���� ������ ������ 1������ ���. ������ �ش� ������ ���
		int pageNo = 1; 
		
		if(request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals("")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		
		System.out.println(pageNo+"������ ��ü ��� ��������.");
		
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

	// �������� ó��
	private PagingInfo pagingProcess(int pageNo) throws NamingException, SQLException {
		PagingInfo pi = new PagingInfo();
		BoardDAO dao = BoardCRUD.getInstance();
		
		pi.setPageNo(pageNo);
		
		// ��ü ���� ���� ��������
		pi.setTotalPostCnt(dao.getTotalPostCnt());
		
		// �� ������ �� 
		pi.setTotalPageCnt(pi.getTotalPostCnt(), pi.getViewPostCntPerPage());

		// �����ֱ� ������ �� index��ȣ
		pi.setStartRowIndex();
		
		// ��ü ����¡ �� ���� 
		pi.setTotalPagingBlockCnt();
		
		// ���� �������� ���� ����¡ �� ��ȣ
		pi.setPageBlockOfCurrentPage();
		
		// ���� ����¡ �� ���� ����¡ �� ��ȣ 
		pi.setStartNumOfCurrentPagingBlock();

		// ���� ����¡ �� �� ����¡ �� ��ȣ 
		pi.setEndNumOfCurrentPagingBlock();
		
		return pi;
	}



}