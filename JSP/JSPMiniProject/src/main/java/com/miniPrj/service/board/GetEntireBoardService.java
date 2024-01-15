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
import com.miniPrj.vo.SearchCriteria;

public class GetEntireBoardService implements BoardService {
	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = BoardCRUD.getInstance();
		
		// �������� ���� ������ ������ 1������ ���. ������ �ش� ������ ���
		int pageNo = 1; 
		String searchType = null;
		String searchWord = null;
		
		if(request.getParameter("pageNo") != null && !request.getParameter("pageNo").equals("")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		if(request.getParameter("searchType") != null && !request.getParameter("searchType").equals("")) {
			searchType = request.getParameter("searchType");
		}
		if(request.getParameter("searchWord") != null && !request.getParameter("searchWord").equals("")) {
			searchWord = request.getParameter("searchWord");
		}
		
		SearchCriteria sc = new SearchCriteria(searchType, searchWord);
		System.out.println(pageNo+"������ ��ü ��� ��������.");
		System.out.println("searchType : " + searchType);
		System.out.println("searchWord : " + searchWord);
		
		try {
			PagingInfo pi;
			if(searchType == null || searchWord == null) {
				pi = pagingProcess(pageNo, null);
			} else {
				pi = pagingProcess(pageNo, sc);
			}
			List<Board> lst = null;
			
			if(searchType != null && searchWord != null && !searchWord.equals("")){
				lst = dao.selectAllBoard(pi, sc);	
			} else {
				lst = dao.selectAllBoard(pi);
			}
			
			System.out.println("lst : "+lst);
			
			if(lst == null && lst.size() == 0) {
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
//	private PagingInfo pagingProcess(int pageNo) throws NamingException, SQLException {
//		PagingInfo pi = new PagingInfo();
//		BoardDAO dao = BoardCRUD.getInstance();
//		
//		pi.setPageNo(pageNo);
//		
//		// ��ü ���� ���� ��������
//		pi.setTotalPostCnt(dao.getTotalPostCnt());
//		
//		// �� ������ �� 
//		pi.setTotalPageCnt(pi.getTotalPostCnt(), pi.getViewPostCntPerPage());
//
//		// �����ֱ� ������ �� index��ȣ
//		pi.setStartRowIndex();
//		
//		// ��ü ����¡ �� ���� 
//		pi.setTotalPagingBlockCnt();
//		
//		// ���� �������� ���� ����¡ �� ��ȣ
//		pi.setPageBlockOfCurrentPage();
//		
//		// ���� ����¡ �� ���� ����¡ �� ��ȣ 
//		pi.setStartNumOfCurrentPagingBlock();
//
//		// ���� ����¡ �� �� ����¡ �� ��ȣ 
//		pi.setEndNumOfCurrentPagingBlock();
//		
//		return pi;
//	}
	
	   // �������� ó��
	   private PagingInfo pagingProcess(int pageNo, SearchCriteria sc) throws NamingException, SQLException {
	      PagingInfo pi = new PagingInfo();
	      BoardDAO dao = BoardCRUD.getInstance();
	      
	      pi.setPageNo(pageNo);
	      
	      // �˻�� ������ �˻��� ���� ����
	      if(sc == null) { //�˻�� ������
	         pi.setTotalPostCnt(dao.getTotalPostCnt());
	      } else if(!sc.getSearchWord().equals("") && !sc.getSearchType().equals("")) {
	         //�˻�� ���� ��
	         pi.setTotalPostCnt(dao.getTotalPostCnt(sc));
	      }
	         
	      
	      // ��ü ���� ���� ��������
//	      pi.setTotalPostCnt(dao.getTotalPostCnt());
	      
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