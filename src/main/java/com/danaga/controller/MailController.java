package com.danaga.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.danaga.dao.OrderDao;
import com.danaga.entity.Orders;
import com.danaga.service.MailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final OrderDao orderDao;

    @PostMapping("/emailauthentication")
    public ResponseEntity<String> joinMailSend(@RequestParam String mail) {
        int number = mailService.joinSendMail(mail);
        return ResponseEntity.ok(String.valueOf(number));
    }

    @PostMapping("/findpassemailauthentication")
    public ResponseEntity<Map<String, String>> findPassMailSend(@RequestParam String mail) throws Exception {
        String randomPass = mailService.findPassSendMail(mail);
        Map<String, String> map = new HashMap<>();
        map.put("newPass", randomPass);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/findidemailauthentication")
    public ResponseEntity<Map<String, String>> findidMailSend(@RequestParam String mail) throws Exception {
        String id = mailService.findIdSendMail(mail);
        Map<String, String> map = new HashMap<>();
        map.put("sendId", id);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/sendOrderIdemailauthentication")
    public ResponseEntity<Map<String, String>> sendOrderIdMailSend(@RequestParam String mail, @RequestParam String orderId) throws Exception {
        String id = mailService.findOrderIdSendMail(mail, orderId);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", id);
        
        if (orderId != null) {
            Orders orders = orderDao.findById(Long.parseLong(orderId));
            if (orders.getMember().getRole().equals("Guest")) {
                orders.getMember().setEmail(null);
            }
            orderDao.save(orders);
            return ResponseEntity.ok(map);
        } else {
            throw new Exception("주문번호가 없습니다.");
        }
    }
}