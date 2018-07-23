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
}
