package com.miniproject.controller.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.domain.Reply;
import com.miniproject.etc.PagingInfo;
import com.miniproject.service.reply.ReplyService;

@RestController
@RequestMapping("/reply/*")
public class ReplyController {
	@Inject
	private ReplyService service; 
	
	@RequestMapping(value = "all/{boardNo}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>>  getAllReplies(@PathVariable("boardNo") int boardNo) {
		System.out.println(boardNo+"번 게시글 댓글 가져오기");
		ResponseEntity<Map<String, Object>> result = null; 
		try {
			PagingInfo pi = new PagingInfo();
			
			List<Reply> lst = service.getAllReplies(boardNo);
			System.out.println("lst : "+lst);
//			result = new ResponseEntity<List<Reply>>(lst, HttpStatus.OK);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("replyList", lst);
			map.put("pagingInfo", pi);
			
			result = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 예외가 발생하면 json으로 응답할 객체가 없어서 상태코드만 전송.
			result = new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		System.out.println("result : "+result);
		return result;
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> saveReply(@RequestBody Reply newReply){ // json으로 넘어온 데이터를 Reply객체로 받을 수 있다. 
		System.out.println(newReply.toString()+"댓글 저장하기");
		ResponseEntity<String> result = null;
		try {
			if(service.saveReply(newReply)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("fail", HttpStatus.FORBIDDEN);
		}
		return result;
	}
	
	
}
