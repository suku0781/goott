package com.miniproject.controller.member;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miniproject.domain.Login;
import com.miniproject.domain.Member;
import com.miniproject.etc.SessionCheck;
import com.miniproject.service.member.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

   private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
   
   @Inject
   MemberService mService;
   
   @RequestMapping(value="login", method=RequestMethod.GET)
   public void loginGET() {
      logger.info("loginGET 방식 호출됨");
   }
   
   @RequestMapping(value="login", method=RequestMethod.POST)
   public String loginPOST(Login tmpMember, Model model, RedirectAttributes rttr) throws Exception {
      System.out.println(tmpMember.toString() + "으로 로그인해보자.");
      
      Member loginMember = mService.login(tmpMember);
      
      if(loginMember != null) {
         System.out.println("로그인 성공!");
         
         model.addAttribute("loginMember", loginMember);
//         model.addAttribute("isLoginSuccess", "success");
         // 이 모델 객체를 가지고 인터셉터 postHandle로 감
         return "index";
      } else {
         System.out.println("로그인 실패");
//         model.addAttribute("isLoginSuccess", "fail");
//         return "redirect:login?fail";
//         rttr.addAttribute("status", "fail"); // 쿼리스트링에 붙여서 보낸다.
         rttr.addFlashAttribute("status", "fail"); // 쿼리스트링으로 가지 않는다.
         return "redirect:login";
      }
      
   }
   
   @RequestMapping("logout")
   public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession ses) {
	   HttpSession reqSes = request.getSession(); // 이렇게 request에서 가져와도 되고 파라미터로 받은 ses에서 가져와도 됨.
	   System.out.println(reqSes.getId() + " 로그아웃. ");
	   
//	   if(reqSes.getAttribute("loginMember") != null) {
//		   reqSes.removeAttribute("loginMember");
//		   reqSes.invalidate();
//	   }
	   
	   // 로그아웃 할 때 세션map에 담긴 세션 제거
	   if((Member)ses.getAttribute("loginMember") != null) {
		   SessionCheck.removeKey(((Member)ses.getAttribute("loginMember")).getUserId());
	   }
	   
	   return "redirect:/";
   }
   
   
}
