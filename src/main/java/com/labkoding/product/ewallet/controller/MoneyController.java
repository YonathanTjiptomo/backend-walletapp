package com.labkoding.product.ewallet.controller;
import com.google.firebase.auth.FirebaseAuthException;
import com.labkoding.product.ewallet.data.ewallet.service.MoneyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/money")
public class MoneyController {
    MoneyService moneyService;

    public MoneyController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getMoney(@RequestParam("uid") String uid) throws FirebaseAuthException {
        BigDecimal amount = moneyService.getMoney(uid);
        Map<String, Object> response = new HashMap<>();
        response.put("amount", amount);
        return ResponseEntity.ok().body(response);
    }
}