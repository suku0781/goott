package com.miniproject.service.reply;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.domain.PointLog;
import com.miniproject.domain.Reply;
import com.miniproject.persistence.MemberDAO;
import com.miniproject.persistence.PointLogDAO;
import com.miniproject.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	ReplyDAO rDao;
	@Inject
	MemberDAO mDao;
	@Inject
	PointLogDAO plDao;
	
	@Override
	public List<Reply> getAllReplies(int boardNo) throws Exception {
		return 	rDao.selectAllReplies(boardNo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
	public boolean saveReply(Reply newReply) throws Exception {
//		댓글 작성 트랜잭션 
//		1. 댓글을 저장한다. 
//		2. 포인트를 1점 부여한다. 
//		3. 포인트로그에 기록한다. 
		boolean result = false;
		if(rDao.insertReply(newReply) == 1) {
			System.out.println("댓글 저장됨.");
			if(mDao.updateUserPoint("답글작성", newReply.getReplier()) == 1) {
//				4. pointLog테이블에 insert한다. 
				if(plDao.insertPointLog(new PointLog(-1, null, "답글작성", 1, newReply.getReplier())) == 1) {
					result = true;
				}
				
			}
		}
		
		
		return result;
	}

}
