package com.gthoya.springmvc.sign.service;

import com.gthoya.springmvc.sign.dao.SignDAO;
import com.gthoya.springmvc.sign.model.User;
import com.gthoya.springmvc.util.CryptComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SignService {
    @Autowired
    private CryptComponent cryptComponent;

    @Autowired
    private SignDAO signDAO;

    public String makeUser(User param) {
        try {
            param.setPassword(cryptComponent.encrypt(param.getPassword()));
        } catch (Exception e) {
            log.error("SignService.makeUser - password encrypt error", e);
            return "password encrypt error";
        }

        User user = signDAO.selectUser(param);

        if (user != null) {
            return "user id already exists";
        }

        if (signDAO.insertUser(param) != 1) {
            return "sign up fail";
        }

        return "success";
    }

    public User getUser(User param) {
        User result;
        try {
            param.setPassword(cryptComponent.encrypt(param.getPassword()));
        } catch (Exception e) {
            log.error("SignService.getUser - password encrypt error", e);
            result = new User();
            result.setMessage("password encrypt error");
            return result;
        }

        result = signDAO.selectUser(param);
        if (result == null) {
            result = new User();
            result.setMessage("user not found (please check ID and password)");
            return result;
        }

        result.setMessage("success");
        return result;
    }
}
