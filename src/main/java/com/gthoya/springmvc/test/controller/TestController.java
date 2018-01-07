package com.gthoya.springmvc.test.controller;

import com.gthoya.springmvc.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("test")
    public ModelAndView test() {
        ModelAndView mav = new ModelAndView("test");

        mav.addObject("test", testService.test());

        return mav;
    }
}
