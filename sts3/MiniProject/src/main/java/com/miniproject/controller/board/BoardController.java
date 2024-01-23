package com.miniproject.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.miniproject.domain.Board;
import com.miniproject.domain.UploadedFile;
import com.miniproject.etc.UploadFileProcess;
import com.miniproject.service.board.BoardService;

/**
 * @package : com.miniproject.controller.board
 * @fileName : BoardController.java
 * @author : shk
 * @date  : 2024. 1. 22. 
 * @description : 
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private List<UploadedFile> ufList = new ArrayList<UploadedFile>();
	
	@Inject
	BoardService bServic;
	
	/**
	 * @MethodName : listAll
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 
	 * @param model
	 */
	@RequestMapping("listAll")
	public void listAll(Model model) {
		logger.info("listAll 호출됨.");
		
		List<Board> lst = bServic.getEntireBoard();
		
		model.addAttribute("boardList", lst); // 게시판 글 목록 바인딩
		
	}
	
	/**
	 * @MethodName : writeBoard
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 
	 */
	@RequestMapping("writeBoard")
	public void writeBoard() {
		logger.info("writeBoard 호출됨.");
		
		
	}
	
	/**
	 * @MethodName : uploadFile
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 
	 * @param uploadFile
	 * @param req
	 */
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public @ResponseBody List<UploadedFile> uploadFile(MultipartFile uploadFile, HttpServletRequest req) {
		logger.info("파일 업로드요청이 들어옴.");
		
		System.out.println("오리지널 파일명 : "+uploadFile.getOriginalFilename());
		System.out.println("파일 사이즈 : "+uploadFile.getSize());
		System.out.println("파일 contentType : "+uploadFile.getContentType());
		
		// 파일이 실제로 저장될 경로 realPath
		String realPath = req.getSession().getServletContext().getRealPath("resources/uploads"); // 파일이 저장되는 실제 경로
		UploadedFile uf = null;
		// 파일처리 
		try {
			uf = UploadFileProcess.fileUpload(uploadFile.getOriginalFilename(), uploadFile.getSize(), uploadFile.getContentType(), uploadFile.getBytes(), realPath);
			if(uf != null) {
				ufList.add(uf);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("리얼패스 : "+realPath);
		
		for(UploadedFile f : ufList) {
			System.out.println("현재 파일 업로드 리스트 : " + f.toString());
		}
		
		return ufList;
	}
	
	@RequestMapping("removeFile")
	public ResponseEntity<String> removeFile(@RequestParam("removeFile") String removeFile, HttpServletRequest req) {
		logger.info(removeFile+"파일 삭제 요청이 들어옴.");
		
		// 파일이 실제로 저장되어있는 경로 realPath
		String realPath = req.getSession().getServletContext().getRealPath("resources/uploads"); // 파일이 실제로 저장되어있는 실제 경로
		ResponseEntity result = null;
		UploadFileProcess.fileDelete(ufList, removeFile, realPath);
		List<UploadedFile> tmpUfList = new ArrayList<UploadedFile>();
		int index = 0;
		
		for(UploadedFile uf : ufList) {
			if(!removeFile.equals(uf.getOriginalFileName())) {
				index++;
			} else if(removeFile.equals(uf.getOriginalFileName())) {
				break;
			}
		}
		
		System.out.println("삭제할 인텍스 : " + index);
		
		ufList.remove(index);
		result = new ResponseEntity<String>("success", HttpStatus.OK);
		
		logger.info("result : "+result);
		return result;
	}
	
	@RequestMapping("removeAllFile")
	public ResponseEntity<String> removeAllFile(HttpServletRequest req) {
		logger.info("게시글 초기화 요청이 들어옴.");
		
		ResponseEntity result = null;
		// 파일이 실제로 저장되어있는 경로 realPath
		String realPath = req.getSession().getServletContext().getRealPath("resources/uploads"); // 파일이 실제로 저장되어있는 실제 경로
		
		UploadFileProcess.fileDelete(ufList, "", realPath);
		result = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return result;
	}
	
}

