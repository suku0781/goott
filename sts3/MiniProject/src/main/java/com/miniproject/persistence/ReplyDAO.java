package com.miniproject.persistence;

import java.util.List;

import com.miniproject.domain.Reply;

public interface ReplyDAO {
	// 모든 댓글 조회
	List<Reply> selectAllReplies(int boardNo) throws Exception;

	int insertReply(Reply newReply);

}
