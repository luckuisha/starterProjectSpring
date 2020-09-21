package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class User {

    @Id
    //@Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_]{7,30}$", message = "ur username cant contain special characters and is limited to 8-30 chars")
    private String username;

   // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{9,18}$", message = "ur pw  must contain capital, lowercase, and a special character, 11-18 chrs")
    private String pw;

    public User(String username, String pw) {
        this.username = username;
        this.pw = pw;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPw() {
        return pw;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

}
