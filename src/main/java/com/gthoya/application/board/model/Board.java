package com.gthoya.application.board.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private long id;
    private String title;
    private String contents;
    private String useYn;
    private LocalDateTime createDateTime;
    private long createUser;
    private LocalDateTime updateDateTime;
    private long updateUser;
}
