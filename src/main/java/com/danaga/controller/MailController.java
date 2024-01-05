package com.danaga.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.danaga.dao.OrderDao;
import com.danaga.entity.Orders;
import com.danaga.service.MailService;
import com.danaga.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final OrderDao orderDao;
    // 회원가입 이메일 인증번호 발송
    @PostMapping("/emailauthentication")
    public String joinMailSend(String mail){

       int number = mailService.joinSendMail(mail);
       // JSON 데이터 String변환
       String num = "" + number;
       
       return num;
    }
    // 임시 비밀번호 발급
    @PostMapping("/findpassemailauthentication")
    public Map findPassMailSend(String mail) throws Exception{
    	
    	String randomPass = null;
		randomPass = mailService.findPassSendMail(mail);
		HashMap map=new HashMap<>();
    	map.put("newPass", randomPass);
    	
    	return map;
    }
    // 회원 아이디 발송
    @PostMapping("/findidemailauthentication")
    public Map findidMailSend(String mail) throws Exception{
    	
    	String id = null;
		id = mailService.findIdSendMail(mail);
    	HashMap map=new HashMap<>();
    	map.put("sendId", id);
    	return map;
    }
    // 주문 번호 발송
    @PostMapping("/sendOrderIdemailauthentication")
    public Map sendOrderIdMailSend(String mail, @RequestParam String orderId) throws Exception{
    	
    	String id = null;
    	id = mailService.findOrderIdSendMail(mail, orderId);
    	HashMap map=new HashMap<>();
    	map.put("orderId", id);
    	if(orderId!=null) {
    		
    		Orders orders= orderDao.findById(Long.parseLong(orderId));
    		if(orders.getMember().getRole().equals("Guest")) {
    			orders.getMember().setEmail(null);
    		}
    		orderDao.save(orders);
    		return map;
    	}else {
    		throw new Exception("주문번호가 없습니다.");
    	}
    }

}