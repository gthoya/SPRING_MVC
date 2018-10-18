package com.gthoya.application.temp;

import com.gthoya.annotation.LoginCheck;
import com.gthoya.application.sign.model.User;
import com.gthoya.constant.CommonConstant;
import com.gthoya.util.FileUploadComponent;
import com.gthoya.util.model.UploadFileForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@PropertySource("classpath:application.properties")
@Slf4j
public class TempController {
    @Value("${contents.attach.filePath}")
    private String contentsAttachFilePath;

    @Autowired
    private FileUploadComponent fileUploadComponent;

    @GetMapping("fileUploadForm")
    public String getFileUploadForm() {
        return "temp/uploadFile";
    }

    @PostMapping("uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile attachFile) {
        try {
            if (attachFile == null) {
                return CommonConstant.FAIL;
            }

            User user = new User();
            user.setId(1);
            UploadFileForm uploadFileForm = fileUploadComponent.uploadFile(user, attachFile, contentsAttachFilePath);

            return CommonConstant.SUCCESS;
        } catch (Exception e) {
            log.error("contents file upload error", e);
            return CommonConstant.FAIL;
        }
    }
}
