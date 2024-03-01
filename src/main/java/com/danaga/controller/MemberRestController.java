package com.danaga.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danaga.dao.MemberDao;
import com.danaga.dto.CartDto;
import com.danaga.dto.KakaoMemberUpdateDto;
import com.danaga.dto.MemberFindDto;
import com.danaga.dto.MemberLoginDto;
import com.danaga.dto.MemberResponseDto;
import com.danaga.dto.MemberUpdateDto;
import com.danaga.dto.product.RecentViewDto;
import com.danaga.entity.Cart;
import com.danaga.entity.Member;
import com.danaga.exception.EmailMismatchException;
import com.danaga.exception.ExistedMemberByEmailException;
import com.danaga.exception.ExistedMemberByNicknameException;
import com.danaga.exception.ExistedMemberByPhoneNoException;
import com.danaga.exception.ExistedMemberByUserNameException;
import com.danaga.exception.MemberNotFoundException;
import com.danaga.exception.PasswordMismatchException;
import com.danaga.service.CartService;
import com.danaga.service.MemberService;
import com.danaga.service.product.RecentViewService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;
    private final MemberDao memberDao;
    private final CartService cartService;
    private final RecentViewService recentViewService;

    @PostMapping(value = "/findid-rest", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> member_findid_action_rest(@RequestBody MemberFindDto memberFindDto, HttpSession session) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int result = 1;

        try {
            memberService.getMemberBy(memberFindDto.getEmail());
        } catch (MemberNotFoundException e) {
            result = 0;
            map.put("result", result);
            map.put("msg", memberFindDto.getEmail() + "는 등록되지 않은 이메일입니다.");
            return ResponseEntity.ok(map);
        }

        map.put("result", result);
        return ResponseEntity.ok(map);
    }

    @PostMapping(value = "/findpass-rest", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> member_findpass_action_rest(@RequestBody MemberFindDto memberFindDto, HttpSession session) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int result = 2;

        try {
            memberService.isMatchEmailByUserName(memberFindDto.getUserName(), memberFindDto.getEmail());
        } catch (MemberNotFoundException e) {
            result = 0;
            map.put("result", result);
            map.put("msg", memberFindDto.getUserName() + "는 존재하지 않는 아이디입니다.");
            return ResponseEntity.ok(map);
        } catch (EmailMismatchException e) {
            result = 1;
            map.put("result", result);
            map.put("msg", "해당 아이디에 등록된 이메일이 아닙니다.");
            return ResponseEntity.ok(map);
        }

        map.put("result", result);
        return ResponseEntity.ok(map);
    }

    @Transactional
    @PostMapping(value = "/login-rest", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> member_login_action_rest(@RequestBody MemberLoginDto memberLoginDto, HttpSession session) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int result = 2;

        try {
            memberService.login(memberLoginDto.getUserName(), memberLoginDto.getPassword());
        } catch (MemberNotFoundException e) {
            result = 0;
            map.put("result", result);
            map.put("member", memberLoginDto);
            return ResponseEntity.ok(map);
        } catch (PasswordMismatchException e) {
            result = 1;
            map.put("result", result);
            return ResponseEntity.ok(map);
        }

        MemberResponseDto loginUser = memberService.getMemberBy(memberLoginDto.getUserName());
        List<CartDto> fUserCarts = (List<CartDto>) session.getAttribute("fUserCarts");
        session.setAttribute("sUserId", loginUser.getUserName());

        if (fUserCarts != null) {
            for (int i = 0; i < fUserCarts.size(); i++) {
                Cart findCart = cartService.findCart(loginUser.getUserName(), fUserCarts.get(i).getOptionSetId());
                if (findCart != null) {
                    if (findCart.getQty() + fUserCarts.get(i).getQty() > 5) {
                        fUserCarts.get(i).setQty(5 - findCart.getQty());
                    }
                }
                cartService.addCart(fUserCarts.get(i), loginUser.getUserName());
            }
            session.setAttribute("fUserCarts", null);
            session.setAttribute("countCarts", cartService.countCarts(loginUser.getUserName()));
        }

        if (session.getAttribute("recentviews") != null) {
            List<Long> recentOptionSetIds = (List<Long>) session.getAttribute("recentviews");
            for (Long optionSetId : recentOptionSetIds) {
                recentViewService.addRecentView(
                        RecentViewDto.builder().optionSetId(optionSetId).memberId(loginUser.getId()).build());
            }
            session.removeAttribute("recentviews");
        }

        if (loginUser.getRole().equals("Admin")) {
            session.setAttribute("role", loginUser.getRole());
        }

        map.put("result", result);
        return ResponseEntity.ok(map);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> member_join_action(@RequestBody MemberResponseDto memberResponseDto) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int result = 5;
        memberResponseDto.setRole("Member");

        try {
            memberService.joinMember(Member.toResponseEntity(memberResponseDto));
        } catch (ExistedMemberByUserNameException e) {
            result = 1;
            map.put("result", result);
            map.put("msg", Member.toResponseEntity(memberResponseDto).getUserName() + "는 사용중인 아이디입니다.");
            return ResponseEntity.ok(map);
        } catch (ExistedMemberByEmailException e) {
            result = 2;
            map.put("result", result);
            map.put("msg", Member.toResponseEntity(memberResponseDto).getEmail() + "는 사용중인 이메일입니다.");
            return ResponseEntity.ok(map);
        } catch (ExistedMemberByPhoneNoException e) {
            result = 3;
            map.put("result", result);
            map.put("msg", Member.toResponseEntity(memberResponseDto).getPhoneNo() + "는 사용중인 번호입니다.");
            return ResponseEntity.ok(map);
        } catch (ExistedMemberByNicknameException e) {
            result = 4;
            map.put("result", result);
            map.put("msg", Member.toResponseEntity(memberResponseDto).getNickname() + "는 사용중인 닉네임입니다.");
            return ResponseEntity.ok(map);
        }

        map.put("result", result);
        return ResponseEntity.ok(map);
    }

    @Transactional
    @PostMapping("/join-rest-kakao")
    public ResponseEntity<Map<String, Object>> member_join_action_kakao(@RequestBody MemberResponseDto memberResponseDto, HttpSession session) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int result = 3;
        try {
            String sUserId = (String) session.getAttribute("sUserId");
            Long sUserLongId = memberService.getMemberBy(sUserId).getId();
            memberResponseDto.setId(sUserLongId);
            MemberResponseDto dto = memberService.kakaoToMember(KakaoMemberUpdateDto.toDto(Member.toResponseEntity(memberResponseDto)));
            int point = dto.getGradePoint() + memberDao.randomPoint();
            dto.setGradePoint(dto.getGradePoint() + memberDao.randomPoint());
            memberService.updateGrade(Member.toResponseEntity(memberResponseDto), point);
        } catch (ExistedMemberByUserNameException e) {
            result = 1;
            map.put("result", result);
            map.put("msg", Member.toResponseEntity(memberResponseDto).getUserName() + "는 사용중인 아이디입니다.");
            return ResponseEntity.ok(map);
        } catch (ExistedMemberByNicknameException e) {
            result = 2;
            map.put("result", result);
            map.put("msg", Member.toResponseEntity(memberResponseDto).getNickname() + "는 사용중인 닉네임입니다.");
            return ResponseEntity.ok(map);
        }
        map.put("result", result);
        session.removeAttribute("role");
        return ResponseEntity.ok(map);
    }

    @LoginCheck
    @PutMapping(value = "/modify-action-rest", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> member_modify_action(@RequestBody MemberUpdateDto memberUpdateDto, HttpSession session) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int result = 2;
        try {
            String sUserId = (String) session.getAttribute("sUserId");
            Long sUserLongId = memberService.getMemberBy(sUserId).getId();
            memberUpdateDto.setId(sUserLongId);
            memberService.updateMember(memberUpdateDto);
        } catch (ExistedMemberByNicknameException e) {
            result = 1;
            map.put("result", result);
            map.put("msg", memberUpdateDto.getNickname() + "는 사용중인 닉네임입니다.");
            return ResponseEntity.ok(map);
        }
        map.put("result", result);
        return ResponseEntity.ok(map);
    }

    @LoginCheck
    @DeleteMapping(value = "/delete-action-rest", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> delete_action_rest(@RequestBody MemberLoginDto memberLoginDto, HttpSession session) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        int result = 2;
        try {
            String sUserId = (String) session.getAttribute("sUserId");
            MemberResponseDto member = memberService.getMemberBy(sUserId);
            if (member.getPassword().equals(memberLoginDto.getPassword())) {
                memberService.deleteMember(member.getUserName());
                session.invalidate();
            } else {
                throw new PasswordMismatchException("비밀번호가 일치하지않습니다.");
            }
        } catch (PasswordMismatchException e) {
            result = 1;
            map.put("result", result);
            map.put("msg", "비밀번호가 일치하지않습니다.");
            return ResponseEntity.ok(map);
        }
        map.put("result", result);
        return ResponseEntity.ok(map);
    }
}