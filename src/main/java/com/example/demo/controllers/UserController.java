package com.example.demo.controllers;


import com.example.demo.db.UserRepoImpl;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.regex.Pattern;


@RestController
public class UserController {

    @Autowired
    private UserRepoImpl userRepImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {

        String username = user.getUsername();
        String pw = user.getPw();

        if (userRepImpl.findByUsername(username).isPresent()) {
            System.out.println("already exists");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,18}$", pw)) {
            System.out.println("pw is bad");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (username.length() < 8 || username.length() > 30 || username.isBlank() || !username.matches("\\S+")){
            System.out.println("username is bad");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User saveUser = new User(username, pw);

        userRepImpl.save(saveUser);
        System.out.println("success");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        String username = user.getUsername();
        String pw = user.getPw();
        Optional<User> currentUser = userRepImpl.findByUsername(username);

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

