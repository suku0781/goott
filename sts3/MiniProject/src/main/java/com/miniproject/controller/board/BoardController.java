package com.miniproject.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	private String realPath = "";
	
	@Inject
	BoardService bService;
	
	/**
	 * @MethodName : listAll
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping("listAll")
	public void listAll(Model model) throws Exception {
		logger.info("listAll 호출됨.");
		
		List<Board> lst = bService.getEntireBoard();
		
		model.addAttribute("boardList", lst); // 게시판 글 목록 바인딩
		
	}
	
	/**
	 * @MethodName : writeBoard
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 
	 */
	@RequestMapping("writeBoard")
	public void writeBoard(HttpSession ses) {
		logger.info("writeBoard 호출됨.");
		
		String uuid = UUID.randomUUID().toString();
		ses.setAttribute("csrfToken", uuid); // 세션에 바인딩
		
		
	}
	
	@RequestMapping(value="writeBoard", method=RequestMethod.POST)
	public void writeBoard(Board newBoard, @RequestParam("csrfToken") String inputscrf, HttpSession ses) {
		logger.info("게시판 글 작성 newBoard : " + newBoard.toString() );
		logger.info("게시판 글 작성 csrfToken : " + inputscrf.toString() );
		logger.info("게시판 글 작성 ses : " + ses.toString() );
		logger.info("게시판 글 작성 (((String)ses.getAttribute(\"csrfToken\")).equals(inputscrf)) : " + (((String)ses.getAttribute("csrfToken")).equals(inputscrf)) );
		logger.info("게시판 글 작성 (((String)ses.getAttribute(\"csrfToken\")).equals(inputscrf)) : " + ses.getAttribute("csrfToken") );
		logger.info("게시판 글 작성 inputscrf : " + inputscrf );
		
		if( (((String)ses.getAttribute("csrfToken")).equals(inputscrf)) ) { //scrfToken이 같을때만 게시글을 저장한다. 
			try {
				bService.saveNewBoard(newBoard, ufList);
				
			} catch (Exception e) {
//				System.out.println("break!");
				e.printStackTrace();
//				for(UploadedFile uf : ufList) {
//					removeFile(uf.getOriginalFileName());
//				}
			}
			
			
			
		}
		
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
		realPath = req.getSession().getServletContext().getRealPath("resources/uploads"); // 파일이 저장되는 실제 경로
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
//	public ResponseEntity<String> removeFile(String removeFile) {
//		logger.info(removeFile+"파일 삭제 요청이 들어옴.");
//		
//		ResponseEntity result = null;
//		UploadFileProcess.fileDelete(ufList, removeFile, realPath);
//		List<UploadedFile> tmpUfList = new ArrayList<UploadedFile>();
//		int index = 0;
//		
//		for(UploadedFile uf : ufList) {
//			if(!removeFile.equals(uf.getOriginalFileName())) {
//				index++;
//			} else if(removeFile.equals(uf.getOriginalFileName())) {
//				break;
//			}
//		}
//		
//		
//		System.out.println("삭제할 인텍스 : " + index);
//		
//		ufList.remove(index);
//		result = new ResponseEntity<String>("success", HttpStatus.OK);
//		
//		logger.info("result : "+result);
//		return result;
//	}
	
	
	@RequestMapping("removeAllFile")
	public ResponseEntity<String> removeAllFile(HttpServletRequest req) {
		logger.info("게시글 초기화 요청이 들어옴.");
		
		ResponseEntity result = null;
		// 파일이 실제로 저장되어있는 경로 realPath
		String realPath = req.getSession().getServletContext().getRealPath("resources/uploads"); // 파일이 실제로 저장되어있는 실제 경로
		
		UploadFileProcess.fileDelete(ufList, realPath);
		result = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return result;
	}
	
}

