package com.gthoya.util.model;

import lombok.Data;

@Data
public class UploadFileForm {
    private int id;
    private int seq;
    private String originalFileName;
    private String fileName;
    private String userId;
}
