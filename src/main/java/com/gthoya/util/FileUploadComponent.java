package com.gthoya.util;

import com.gthoya.application.sign.model.User;
import com.gthoya.util.model.UploadFileForm;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FileUploadComponent {
    public UploadFileForm uploadFile(User user, MultipartFile attachFile, String filePath) throws Exception {
        if (attachFile == null) {
            return null;
        }

        String fileName = user.getId() + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".jpg";

        UploadFileForm uploadFileForm = new UploadFileForm();
        uploadFileForm.setOriginalFileName(attachFile.getOriginalFilename());
        uploadFileForm.setFileName(filePath + fileName);
        uploadFileForm.setUserId(user.getUserId());

        File file = new File(filePath + fileName);
        attachFile.transferTo(file);

        return uploadFileForm;
    }
}
