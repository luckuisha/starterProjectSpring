package com.example.demo.db;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<User> findByUsername(String username) {
        return userRepo.findById(username);
    }
}
