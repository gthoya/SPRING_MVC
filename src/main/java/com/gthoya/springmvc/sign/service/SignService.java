package com.gthoya.springmvc.sign.service;

import com.gthoya.springmvc.sign.dao.SignDAO;
import com.gthoya.springmvc.sign.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignService {
    @Autowired
    private SignDAO signDAO;

    public String makeUser(User param) {
        User user = signDAO.selectUser(param);

        if (user != null) {
            return "user id already exists";
        }

        if (signDAO.insertUser(param) != 1) {
            return "sign up fail";
        }

        return "";
    }

    public User getUser(User param) {
        User result = signDAO.selectUser(param);
        if (result == null) {
            result = new User();
            result.setMessage("user not found (please check ID and password)");
        }

        return result;
    }
}
