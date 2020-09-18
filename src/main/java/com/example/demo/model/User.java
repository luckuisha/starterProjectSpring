package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    private String username;

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
