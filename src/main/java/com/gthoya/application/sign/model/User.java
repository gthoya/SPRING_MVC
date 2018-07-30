package com.gthoya.application.sign.model;

import lombok.Data;

@Data
public class User {
    private long id;
    private String userId;
    private String password;
    private String userName;
    private int age;
    private String gender;
    private String createDate;

    private String message;

    public User() {

    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
