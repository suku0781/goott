package com.miniPrj.etc;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class SendMail {
	public static void sendMail(String userEmail, String code) throws AddressException, MessagingException {
		// SMTP (Simple Mail Transfer Protocol) : 메일 전송 통신규약
	      
	      Properties props = new Properties();
	      
	      String subject = "jspminiproj.com에서 보낸 이메일 인증번호입니다";
	      String message = "<h1>인증코드 : " + code + "</h1><br><p>코드를 입력하여 회원가입을 완료하십시오.<a href='http://localhost:8081/JSPMiniProject/'>홈페이지로 이동</a></p>";
	      
	      // gmail서버에 따르는 SMTP환경 설정
	      props.put("mail.smtp.starttls.required",  "true"); // 메일 서버 환경 설정 시작
	      props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 사용될 ssl보안 프로토콜 설정
	      props.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 호스트이름
//	      props.put("mail.smtp.host", "smtp.naver.com"); // smtp 서버 호스트이름
	      props.put("mail.smtp.port", "465"); // smpt 포트번호
	      props.put("mail.smtp.auth", "true"); // 인증과정 거치겠다.
	      props.put("mail.smtp.ssl.enable", "true"); // SSL사용
	      
	      Session mailSession = Session.getInstance(props, new Authenticator() {
	         
	         @Override
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new javax.mail.PasswordAuthentication(EmailAccount.emailAddress, EmailAccount.emailPassword);
	         }
	      
	      });
	      
	      
	      System.out.println("mail세션: " + mailSession.toString());
	      
	      if (mailSession != null) {
	         MimeMessage mime = new MimeMessage(mailSession);
	         
	         mime.setFrom(new InternetAddress("miniPrj@team.com"));// 보내는 메일 주소
	         mime.addRecipient(RecipientType.TO, new InternetAddress(userEmail)); // 받는 사람
	         
	         mime.setSubject(subject); // 제목
	         mime.setText(message, "utf-8", "html"); // 메일 본문
	         
	         Transport trans = mailSession.getTransport("smtp");
	         trans.connect(EmailAccount.emailAddress, EmailAccount.emailPassword);
	         trans.sendMessage(mime, mime.getAllRecipients()); // 발송
	         trans.close();
	      }
	}
}
