package com.gthoya.application.board.model;

import lombok.Data;

@Data
public class Contents {
    private long id;
    private String title;
    private String contentsBody;
    private String useYn;
    private String createDateTime;
    private long createUser;
    private String createUserName;
    private String updateDateTime;
    private long updateUser;
    private String updateUserName;

    public Contents() {

    }

    public Contents(long id) {
        this.id = id;
    }
}
