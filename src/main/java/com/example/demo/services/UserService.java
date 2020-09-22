package com.example.demo.services;

import com.example.demo.db.UserRepoImpl;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepoImpl userRepImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity userValidAndSave(String username, String pw){

        if (userRepImpl.findByUsername(username).isPresent()) {
            //throw new ResponseStatusException(HttpStatus.CONFLICT, "user with username already exists");
            System.out.println("user already existsd");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,18}$", pw)) {
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "pw must contain a special char, uppercase, lowercase, and numbers. must be between 11-18 chars");
            System.out.println("pw is bad");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (username.length() < 8 || username.length() > 30 || username.isBlank() || !username.matches("\\S+")){
            System.out.println("username is bad");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username can only contain chars, umbers, and a -. must be 8-30 chars");
        }

        User saveUser = new User(username, pw);

        userRepImpl.save(saveUser);
        System.out.println("success");
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity userExistAndPWMatch(String username, String pw){

        Optional<User> currentUser = userRepImpl.findByUsername(username);

        if (currentUser.isPresent()) {
            User existingUser = currentUser.get();
            if (bCryptPasswordEncoder.matches(pw, existingUser.getPw())) {
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
