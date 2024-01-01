package com.danaga.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.danaga.dao.OrderDao;
import com.danaga.dto.MemberResponseDto;
import com.danaga.dto.MemberUpdateDto;
import com.danaga.entity.Member;
import com.danaga.entity.Orders;
import com.danaga.generator.RandomStringGenerator;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;
	private final MemberService memberService;
	// 랜덤 숫자 5자리
	private static int number;
	private static String randomString;
	private final OrderDao orderDao;
	// 랜덤 숫자 5자리 생성 및 전역변수 할당 메소드
	public static void createNumber() {
		number = (int) (Math.random() * (90000)) + 10000;// (int) Math.random() * (최댓값-최소값+1) + 최소값
	}

	public static void createRandomPass() throws NoSuchAlgorithmException {
		randomString = RandomStringGenerator.generateRandomString();
	}
	// 회원가입 인증 메일 생성 메소드
	public MimeMessage joinCreateMail(String mail) {
		// 랜덤 숫자 5자리 생성 및 전역변수 할당
		createNumber();
		// javaMailSender의 MimeMessage 객체 생성
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			// 인자로 받은 mail로 수신자 설정
			message.setRecipients(MimeMessage.RecipientType.TO, mail);
			// 제목 설정
			message.setSubject("다나가 이메일 인증");
			// 내용 설정
			String body = "";
			body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
			body += "<h1>" + number + "</h1>";
			body += "<h3>" + "감사합니다." + "</h3>";
			message.setText(body, "UTF-8", "html");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}
	// 회원가입 인증 메일 보내기 메소드
	public int joinSendMail(String mail) {

		MimeMessage message = joinCreateMail(mail);

		javaMailSender.send(message);
		
		return number;
	}

	public MimeMessage orderCreateMail(String mail, String orderId) throws Exception{
	    MimeMessage message = javaMailSender.createMimeMessage();
	    try {
	        message.setRecipients(MimeMessage.RecipientType.TO, mail);
	        message.setSubject("다나가 주문번호 확인");
	        String body = "";
	        body += "<h3>" + "주문하신 번호는" + "</h3>";
	        body += "<h1>" + orderId +  "</h1>" +"입니다.";
	        body += "<h3>" + "감사합니다." + "</h3>";
	        message.setText(body,"UTF-8", "html");
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	    return message;
	}
	
	public String findOrderIdSendMail(String mail, String orderId) throws Exception {
		MimeMessage message = orderCreateMail(mail, orderId);
		javaMailSender.send(message);
		return orderId;
	}

	

	public MimeMessage findPassCreateMail(String mail) throws Exception {
		createRandomPass();
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MemberResponseDto dto = memberService.getMemberBy(mail);
			dto.setPassword(randomString);
			memberService.updateMember(MemberUpdateDto.toDto(Member.toResponseEntity(dto)));
			// message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(MimeMessage.RecipientType.TO, mail);
			message.setSubject("다나가 임시 비밀번호 발급");
			String body = "";
			body += "<h3>" + "요청하신 임시 비밀번호입니다." + "</h3>";
			body += "<h1>" + randomString + "</h1>";
			body += "<h3>" + "감사합니다." + "</h3>";
			message.setText(body, "UTF-8", "html");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}

	public String findPassSendMail(String mail) throws Exception {

		MimeMessage message = findPassCreateMail(mail);

		javaMailSender.send(message);

		return randomString;
	}

	public MimeMessage findIdCreateMail(String mail) throws Exception {
		createNumber();
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			// message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(MimeMessage.RecipientType.TO, mail);
			message.setSubject("다나가 아이디 찾기");
			String body = "";
			body += "<h3>" + "요청하신 아이디 입니다." + "</h3>";
			body += "<h1>" + memberService.getMemberBy(mail).getUserName() + "</h1>";
			body += "<h3>" + "감사합니다." + "</h3>";
			message.setText(body, "UTF-8", "html");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}

	public String findIdSendMail(String mail) throws Exception {

		MimeMessage message = findIdCreateMail(mail);

		javaMailSender.send(message);

		return memberService.getMemberBy(mail).getUserName();
	}

	
}