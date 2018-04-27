package com.gthoya.springmvc.test.controller;

import com.gthoya.springmvc.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("test")
    public ModelAndView test() {
        ModelAndView mav = new ModelAndView("test");
        log.debug("###################### test before #######################");
        mav.addObject("test", testService.test());
        log.debug("###################### test after #######################");
        return mav;
    }
}
