package com.gthoya.application.sign.controller;

import com.gthoya.constant.CommonConstant;
import com.gthoya.application.sign.model.User;
import com.gthoya.application.sign.service.SignService;
import com.gthoya.util.HttpSessionUtils;
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

    @GetMapping("signUp")
    public String getSignUpPage() {
        return "sign/signUp";
    }

    @PostMapping("signUp")
    @ResponseBody
    public User signUp(HttpSession httpSession, User param) {
        User user = new User();

        if (!validateParam4SignUp(param, user)) {
            return user;
        }

        try {
            String result = signService.createUser(param);
            if (!StringUtils.equals(result, CommonConstant.SUCCESS)) {
                user.setMessage(result);
                return user;
            }

            user = signIn(httpSession, param);
        } catch (Exception e) {
            log.error("sign up fail - {}", param.getUserId());
            user.setMessage(CommonConstant.FAIL);
        }

        return user;
    }

    @GetMapping("signIn")
    public String getSignInPage() {
        return "sign/signIn";
    }

    @PostMapping("signIn")
    @ResponseBody
    public User signIn(HttpSession httpSession, User param) {
        User user = new User();

        if (!validateParam4SignIn(param, user)) {
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

    private boolean validateParam4SignUp(User param, User user) {
        if (!validateParam4SignIn(param, user)) {
            return false;
        } else if (StringUtils.isEmpty(param.getUserName())) {
            user.setMessage("user name is empty");
            return false;
        } else if (param.getAge() == 0) {
            user.setMessage("age is empty");
            return false;
        } else if (StringUtils.isEmpty(param.getGender())) {
            user.setMessage("gender is empty");
            return false;
        }

        return true;
    }

    private boolean validateParam4SignIn(User param, User user) {
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

        if (httpSession.getAttribute(HttpSessionUtils.USER_SESSION_KEY) != null) {
            httpSession.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        }

        httpSession.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
    }

    @GetMapping("/signOut")
    public void signOut(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
    }
}
