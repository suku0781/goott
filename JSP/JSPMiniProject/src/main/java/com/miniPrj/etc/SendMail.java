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
		// SMTP (Simple Mail Transfer Protocol) : ���� ���� ��űԾ�
	      
	      Properties props = new Properties();
	      
	      String subject = "jspminiproj.com���� ���� �̸��� ������ȣ�Դϴ�";
	      String message = "<h1>�����ڵ� : " + code + "</h1><br><p>�ڵ带 �Է��Ͽ� ȸ�������� �Ϸ��Ͻʽÿ�.<a href='http://localhost:8081/JSPMiniProject/'>Ȩ�������� �̵�</a></p>";
	      
	      // gmail������ ������ SMTPȯ�� ����
	      props.put("mail.smtp.starttls.required",  "true"); // ���� ���� ȯ�� ���� ����
	      props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // ���� ssl���� �������� ����
	      props.put("mail.smtp.host", "smtp.gmail.com"); // smtp ���� ȣ��Ʈ�̸�
//	      props.put("mail.smtp.host", "smtp.naver.com"); // smtp ���� ȣ��Ʈ�̸�
	      props.put("mail.smtp.port", "465"); // smpt ��Ʈ��ȣ
	      props.put("mail.smtp.auth", "true"); // �������� ��ġ�ڴ�.
	      props.put("mail.smtp.ssl.enable", "true"); // SSL���
	      
	      Session mailSession = Session.getInstance(props, new Authenticator() {
	         
	         @Override
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new javax.mail.PasswordAuthentication(EmailAccount.emailAddress, EmailAccount.emailPassword);
	         }
	      
	      });
	      
	      
	      System.out.println("mail����: " + mailSession.toString());
	      
	      if (mailSession != null) {
	         MimeMessage mime = new MimeMessage(mailSession);
	         
	         mime.setFrom(new InternetAddress("miniPrj@team.com"));// ������ ���� �ּ�
	         mime.addRecipient(RecipientType.TO, new InternetAddress(userEmail)); // �޴� ���
	         
	         mime.setSubject(subject); // ����
	         mime.setText(message, "utf-8", "html"); // ���� ����
	         
	         Transport trans = mailSession.getTransport("smtp");
	         trans.connect(EmailAccount.emailAddress, EmailAccount.emailPassword);
	         trans.sendMessage(mime, mime.getAllRecipients()); // �߼�
	         trans.close();
	      }
	}
}
