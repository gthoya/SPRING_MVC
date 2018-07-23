package com.gthoya.application.sign.controller;

import com.gthoya.application.constant.CommonConstant;
import com.gthoya.application.sign.model.User;
import com.gthoya.application.sign.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class SignController {
    @Autowired
    private SignService signService;

    @GetMapping(value = {"", "main"})
    public String getMainPage() {
        return "main/main";
    }

    @GetMapping("signUpPage")
    public String getSignUpPage() {
        return "sign/signUp";
    }

    @PostMapping("signUp")
    @ResponseBody
    public User signUp(HttpSession httpSession, User param) {
        User user = new User();


        if (!validateParam(param, user)) {
            return user;
        }

        try {
            user.setMessage(signService.createUser(param));
            makeSession(httpSession, param);
        } catch (Exception e) {
            log.error("sign up fail - {}", param.getUserId());
            user.setMessage(CommonConstant.FAIL);
        }

        return user;
    }

    @GetMapping("signInPage")
    public String getSignInPage() {
        return "sign/signIn";
    }

    @PostMapping("signIn")
    @ResponseBody
    public User signIn(HttpSession httpSession, User param) {
        User user = new User();

        if (!validateParam(param, user)) {
            return user;
        }

        try {
            user = signService.getUser(param);
            makeSession(httpSession, user);
        } catch (Exception e) {
            log.error("sign in fail - {}", param.getUserId());
            user.setMessage(CommonConstant.FAIL);
        }

        return user;
    }

    private boolean validateParam(User param, User user) {
        if (StringUtils.isEmpty(param.getUserId())) {
            user.setMessage("user id is empty");
            return false;
        } else if (StringUtils.isEmpty(param.getPassword())) {
            user.setMessage("password is empty");
            return false;
        }

        return true;
    }

    private void makeSession(HttpSession httpSession, User user) {
        if (!StringUtils.equals(user.getMessage(), CommonConstant.SUCCESS)) {
            return;
        }

        if (httpSession.getAttribute(user.getUserId()) != null) {
            httpSession.removeAttribute(user.getUserId());
        }

        httpSession.setAttribute(user.getUserId(), user);
    }
}
