package com.danaga.kakao;


import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.danaga.dto.MemberResponseDto;
import com.danaga.entity.Member;
import com.danaga.exception.MemberNotFoundException;
import com.danaga.generator.RandomStringGenerator;
import com.danaga.service.MemberService;

import jakarta.servlet.http.HttpSession;


@Controller
public class KaKaoController {
	@Autowired
	private KaKaoService kaKaoService;
	@Autowired
	private MemberService memberService;
	@Value("${api.kakao.rest_api_key}")
	private String kakaoRestApiKey;

	@Value("${api.kakao.javascript_key}")
	private String kakaoJavascriptApiKey;
	
	@Value("${api.kakao.redirect_url}")
	private String redirect_url;
	
	/*
    - redirection url로 등록된 요청
    - kakao가 발행한 code를 파라메타로 붙혀서 kakao가 호출해준다.
    - ajax방식으로는 불가능하다.
     */
	@GetMapping("/kakao_login_action")
	public String kakao_login_action(String code, HttpSession session) throws Exception {
		
		OAuthToken accessToken = kaKaoService.getToken(code);
		KakaoProfile kakaoProfile = kaKaoService.getKakaoProfile(accessToken);
	    
	    String kakaoUserEmail = kakaoProfile.getKakao_account().getEmail();
	    
	    try {
	    	MemberResponseDto originMember = memberService.getMemberBy(kakaoUserEmail);
			session.setAttribute("sUserId", originMember.getEmail());
			session.setAttribute("role",originMember.getRole());
			
		} catch (MemberNotFoundException e) {
			try {
				String kakaoUserName = "Kakao"+kakaoProfile.getId();
			    String garbagePasswordStr = UUID.randomUUID()+"";
			    
				String birthday = kakaoProfile.getKakao_account().getBirthday();
			    String month = birthday.substring(0, 2);
		        String day = birthday.substring(2);
			    String dateString = kakaoProfile.getKakao_account().getBirthyear()+"-"+month+"-"+day; // 예시 문자열 날짜
		        String pattern = "yyyy-MM-dd";
		        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		        
				MemberResponseDto kakaoMember = memberService.joinMember(Member.builder()
						.userName(kakaoUserName)
						.password(garbagePasswordStr)
						.email(kakaoUserEmail)
						.name(kakaoProfile.getKakao_account().getName())
						.nickname(kakaoUserName+RandomStringGenerator.generateRandomString())
						.birthday(sdf.parse(dateString))
						.phoneNo("0"+kakaoProfile.getKakao_account().getPhone_number().replaceAll("\\+82", "").replaceAll(" ", ""))
						.role("Kakao")
						.build());
				session.setAttribute("sUserId", kakaoUserEmail);
				session.setAttribute("role",kakaoMember.getRole());
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 카카오" +session.getAttribute("sUserId"));
				return "redirect:/member_join_complete_page";
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return "redirect:/index";
	}
	
	
}
