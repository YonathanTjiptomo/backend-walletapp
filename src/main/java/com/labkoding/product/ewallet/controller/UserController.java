package com.labkoding.product.ewallet.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.labkoding.product.ewallet.data.ewallet.model.TbUser;
import com.labkoding.product.ewallet.data.ewallet.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save-user")
    public void saveUser(@RequestParam("uid") String uid, @RequestParam("email") String email) throws FirebaseAuthException {
        System.out.println("UID: " + uid);
        userService.saveUser(uid, email);
    }
    @GetMapping("/get-va")
    public ResponseEntity<Map<String, Object>> saveVirtualAccount(@RequestParam("uid") String uid) throws FirebaseAuthException {
        String virtualAccount = userService.getVa(uid);
        Map<String, Object> response = new HashMap<>();
        response.put("virtualAccount", virtualAccount);
        return ResponseEntity.ok().body(response);
    }
}
