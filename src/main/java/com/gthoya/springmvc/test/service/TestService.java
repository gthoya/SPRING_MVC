package com.gthoya.springmvc.test.service;

import com.gthoya.springmvc.test.dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private TestDAO testDAO;

    public String test() {
        return testDAO.test();
    }
}
