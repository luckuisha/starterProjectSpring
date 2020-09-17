package com.example.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepoImpl {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPw(bCryptPasswordEncoder.encode(user.getPw()));
        userRepo.save(user);
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }
}
