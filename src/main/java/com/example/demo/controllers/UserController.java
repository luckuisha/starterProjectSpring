package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {

        String username = user.getUsername();
        String pw = user.getPw();

        return userService.userValidAndSave(username, pw);
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        String username = user.getUsername();
        String pw = user.getPw();
        return userService.userExistAndPWMatch(username, pw);

    }


}

