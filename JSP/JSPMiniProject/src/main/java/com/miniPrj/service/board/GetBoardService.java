package com.miniPrj.service.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.controller.BoardFactory;
import com.miniPrj.dao.BoardCRUD;
import com.miniPrj.dao.BoardDAO;
import com.miniPrj.service.BoardService;
import com.miniPrj.vo.Board;

public class GetBoardService implements BoardService {

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, NamingException, SQLException {
		System.out.println(request.getParameter("no")+"번째 글 가져오기");
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 클라이언트 ip주소 얻어오기 
		String userIp = getIp(request);
		
		BoardDAO dao = BoardCRUD.getInstance();
		
		try {
			//		해당 아이피 주소와 글 번호가 같은게 있는지 없는지 찾아보기
			if(dao.selectReadCountProcess(userIp, no)) {// 해당 아이피 주소와 글번호 같은 것이 있다면
				if(dao.selectHourDiff(userIp, no) > 23) {//	1. 시간이 24시간 지난 경우
//				-> 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 update
//				-> 조회수 1증가(update)
					
					dao.readCountProcessWithReadCntInc(userIp, no, "update");	
				} 
			} else {//		해당 아이피 주소와 글번호 같은 것이 없다면
//			-> 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 insert
//			-> 해당 글 번호의 readCound를 증가(update)
				dao.readCountProcessWithReadCntInc(userIp, no, "insert");
			}
//		-> 해당 글을 가져옴.(select)
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
