package com.labkoding.product.ewallet.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.labkoding.product.ewallet.data.ewallet.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void saveUser(@RequestParam("uid") String uid, @RequestParam("email") String email) throws FirebaseAuthException {
        System.out.println("UID: " + uid);
        userService.saveUser(uid, email);
    }
}
