package com.miniPrj.service.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.controller.BoardFactory;
import com.miniPrj.dao.BoardCRUD;
import com.miniPrj.dao.BoardDAO;
import com.miniPrj.etc.UploadedFile;
import com.miniPrj.service.BoardService;
import com.miniPrj.vo.Board;

public class GetBoardServiceByNo implements BoardService {

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, NamingException, SQLException {
		System.out.println(request.getParameter("no")+"번째 글 가져오기");
		int no = Integer.parseInt(request.getParameter("no"));
		String page = request.getParameter("page");
		int result = -1;
		// 클라이언트 ip주소 얻어오기 
		String userIp = getIp(request);
		
		BoardDAO dao = BoardCRUD.getInstance();
		
		try {
			if(page.equals("boardDetail")) {
				//		해당 아이피 주소와 글 번호가 같은게 있는지 없는지 찾아보기
				if(dao.selectReadCountProcess(userIp, no)) {// 해당 아이피 주소와 글번호 같은 것이 있다면
					if(dao.selectHourDiff(userIp, no) > 23) {//	1. 시간이 24시간 지난 경우
//				-> 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 update
//				-> 조회수 1증가(update)
						
						result = dao.readCountProcessWithReadCntInc(userIp, no, "update");	
					} 
				} else {//		해당 아이피 주소와 글번호 같은 것이 없다면
//			-> 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 insert
//			-> 조회수 1증가(update)
					result = dao.readCountProcessWithReadCntInc(userIp, no, "insert");
				}
			}
			
			Board board = dao.selectBoardByNo(no);
			List<Board> replyBoardList = dao.selectReplyBoard(no);
			
			UploadedFile uf = dao.getFile(no);
//			-> 해당 글을 가져옴.(select)
			if(board != null) {
				request.setAttribute("board", board);
				request.setAttribute("replyBoardList", replyBoardList);
				request.setAttribute("uploadedFile", uf);
				request.getRequestDispatcher(page+".jsp").forward(request, response);
			}
			
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage(); // String 으로 반환
			e.getStackTrace(); // array로 반환
			
			request.setAttribute("errorMsg", e.getMessage());
			request.setAttribute("errorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
		}
		
		return null;
	}

	private String getIp(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    
	    System.out.println(">>>> X-FORWARDED-FOR : " + ip);

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	        System.out.println(">>>> Proxy-Client-IP : " + ip);
	    }

	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
	        System.out.println(">>>> WL-Proxy-Client-IP : " + ip);
	    }

	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	        System.out.println(">>>> HTTP_CLIENT_IP : " + ip);
	    }

	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        System.out.println(">>>> HTTP_X_FORWARDED_FOR : " + ip);
	    }

	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }

	    System.out.println(">>>> Result : IP Address : " + ip);
	    
	    return ip;
	}
	
	

}
