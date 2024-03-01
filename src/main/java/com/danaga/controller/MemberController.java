package com.danaga.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.danaga.dto.MemberResponseDto;
import com.danaga.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/member-login-form")
	public String member_login_form() {
		return "member/member-login-form";
	}


	@GetMapping("/member-join-form")
	public String member_join_form() {
		return "member/member-join-form";
	}
	@GetMapping("/member-join-complete-page")
	public String member_join_complete_page() {
		return "member/member-join-complete-page";
	}

	@GetMapping("/member-find-id-password-form")
	public String member_findpassword_form() {
		return "member/member-find-id-password-form";
	}

	@LoginCheck
	@GetMapping("/member-info-form")
	public String member_info_form(HttpSession session, Model model) throws Exception {
		/************** login check **************/
		/****************************************/
		String loginUser = (String) session.getAttribute("sUserId");
		MemberResponseDto member = memberService.getMemberBy(loginUser);
		model.addAttribute("loginUser", member);
		return "member/member-info-form";
	}
	@LoginCheck
	@GetMapping("/member-join-form-kakao")
	public String member_join_form_kakao(HttpSession session, Model model) throws Exception {
		/************** login check **************/
		/****************************************/
		String loginUser = (String) session.getAttribute("sUserId");
		MemberResponseDto member = memberService.getMemberBy(loginUser);
		model.addAttribute("loginUser", member);
		return "member/member-join-form-kakao";
	}


	@LoginCheck
	@GetMapping("/member-logout-action")
	public String member_logout_action(HttpSession session) {
		/************** login check **************/
		/****************************************/
		session.invalidate();

		return "redirect:index";
	}
	@LoginCheck
	@GetMapping("/member-quit-form")
	public String member_quit_form() {
		
		return "member/member-quit";
	}


	@GetMapping({ "/member-join-action", "/member-login-action", "/member-modify-action", "/member-remove-action" })
	public String user_get() {
		return "redirect:index";
	}
}
