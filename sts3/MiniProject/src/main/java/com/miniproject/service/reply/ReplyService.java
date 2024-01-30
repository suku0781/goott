package com.miniproject.service.reply;

import java.util.List;

import com.miniproject.domain.Reply;

public interface ReplyService {
	// 모든 댓글 가져오기 
	List<Reply> getAllReplies(int boardNo) throws Exception;

	// 댓글 저장하기
	boolean saveReply(Reply newReply) throws Exception;
}
