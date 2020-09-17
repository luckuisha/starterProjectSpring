package com.example.demo.controllers;

import com.example.demo.db.UserRepo;
import com.example.demo.db.UserRepoImpl;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.regex.Pattern;


@RestController
public class UserController {
    private User user;

    @Autowired
    UserRepoImpl userRepImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String register(@RequestParam("username") String name, @RequestParam("pw") String pw){
        System.out.println(pw.length());
        if (!Pattern.matches("^(?=.*\\d)(?=.*[A-Z]).{10,18}$", pw)){
            System.out.println("pw is bad");
        }
        System.out.println(name + pw);
        user = new User(name, pw);
        System.out.println(user.getUsername() + user.getPw());
        if (userRepImpl.findByUsername(name) == null){
            System.out.println("already exists");
        }
        else {
            userRepImpl.save(user);
            System.out.println("success");
        }
        return user.getUsername();
    }

    @GetMapping("/login")
    public void login(String name, String pw){
       // User currentUser = userRepImpl.findByUsername(name);
    }
}

