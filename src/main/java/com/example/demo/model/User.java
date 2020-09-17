package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="ABC")
public class User {

    @Id
    @Size(min=8, max=30, message = "must be 8-30 chars")
    @NotBlank
    private String username;

    private String pw;

    public User(String username, String pw){
        this.username = username;
        this.pw = pw;
    }
    public User(){}

    public String getUsername(){
        return username;
    }

    public String getPw(){
        return pw;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPw(String pw){
        this.pw = pw;
    }

}
