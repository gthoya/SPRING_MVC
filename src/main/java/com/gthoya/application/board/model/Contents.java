package com.gthoya.application.board.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Contents {
    private long id;
    private String title;
    private String contents;
    private String useYn;
    private String createDateTime;
    private long createUser;
    private String updateDateTime;
    private long updateUser;
}
