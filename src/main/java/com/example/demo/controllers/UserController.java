package com.example.demo.controllers;


import com.example.demo.db.UserRepoImpl;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.regex.Pattern;


@RestController
public class UserController {
    private User user;

    @Autowired
    private UserRepoImpl userRepImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity register(@RequestParam("username") String name, @RequestParam("pw") String pw) {

        if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,18}$", pw)) {
            System.out.println("pw is bad");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (name.length() < 8 || name.length() > 30 || name.isBlank() || !name.matches("\\S+")){
            System.out.println("username is bad");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (userRepImpl.findByUsername(name).isPresent()) {
            System.out.println("already exists");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        user = new User(name, pw);
        userRepImpl.save(user);
        System.out.println("success");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestParam("username") String name, @RequestParam("pw") String pw) {
        Optional<User> currentUser = userRepImpl.findByUsername(name);

        if (currentUser.isPresent()) {
            user = currentUser.get();
            if (bCryptPasswordEncoder.matches(pw, user.getPw())) {
                System.out.println("login success");
                return new ResponseEntity(HttpStatus.OK);
            } else {
                System.out.println("wrong password");
                new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }

        System.out.println("user doesnt exist");
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }


}

