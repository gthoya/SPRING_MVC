package com.gthoya.application.sign.controller;

import com.gthoya.application.sign.model.User;
import com.gthoya.application.sign.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class SignController {
    @Autowired
    private SignService signService;

    @GetMapping("main")
    public String getSignOut() {
        return "main/main";
    }

    @GetMapping("signUpPage")
    public String getSignUpPage() {
        return "sign/signUp";
    }

    @PostMapping("signUp")
    @ResponseBody
    public User signUp(User param) {
        try {
            String result = signService.makeUser(param);
            if (StringUtils.isNotEmpty(result)) {
                param.setMessage(result);
            }
        } catch (Exception e) {
            log.error("sign up fail - {}", param.getUserId());
        }

        return param;
    }

    @GetMapping("signInPage")
    public String getSignInPage() {
        return "sign/signIn";
    }

    @PostMapping("signIn")
    @ResponseBody
    public User signIn(User param) {
        User user = new User();
        try {
            user = signService.getUser(param);
        } catch (Exception e) {
            log.error("sign in fail - {}", param.getUserId());
        }

        return user;
    }
}
