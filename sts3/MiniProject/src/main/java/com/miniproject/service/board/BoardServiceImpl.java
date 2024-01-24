package com.miniproject.service.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.domain.Board;
import com.miniproject.domain.PointLog;
import com.miniproject.domain.UploadedFile;
import com.miniproject.persistence.BoardDAO;
import com.miniproject.persistence.MemberDAO;
import com.miniproject.persistence.PointLogDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	BoardDAO dao;
	@Inject
	MemberDAO mDao;
	@Inject
	PointLogDAO plDao;
	
	@Override
	public List<Board> getEntireBoard() throws Exception {
		List<Board> lst = dao.selectAllBoard();
		
		return lst;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveNewBoard(Board newBoard, List<UploadedFile> ufList) throws Exception {

//		새 게시글 저장 트랜잭션
//		1. board테이블에 글을 저장(insert)하고 그 후 파일을 저장해야한다. 
		if(dao.insertNewBoard(newBoard) == 1) { // insert되엇다면
			
			int boardNo = dao.selectBoardNo(); // boardNo최대값
			
//			2. 업로드파일이 있는경우
			if(ufList.size() > 0) { 
//				저장할때 insert된 글의 no값을 얻어와서 uploadedFile테이블에 파일정보를 저장(insert)한다. 
				for(UploadedFile file : ufList) {
					System.out.println("테이블에 저장될 uf : " + file.toString());
					dao.insertUploadedFile(file, boardNo);
				}
				System.out.println("boardNo : " + boardNo);
			}
			
//			3. member테이블에 userPoint를 pointPolicy대로 update한다.
			mDao.updateUserPoint("게시물작성", newBoard.getWritter());
			
//			4. pointLog테이블에 insert한다. 
			plDao.insertPointLog(new PointLog(-1, null, "게시물작성", 2, newBoard.getWritter()));
			
			
		}
		
//		이 중에서 하나라도 실패한다면 모두 롤백해야한다. (all or nothing)
	}

}
