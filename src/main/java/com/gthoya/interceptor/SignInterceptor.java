package com.gthoya.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class SignInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.debug("---------------------------- signInterceptor ----------------------------");
        String userId = request.getParameter("userId");
        HttpSession session = request.getSession();

        if (userId != null && session.getAttribute(userId) != null) {
            log.debug("==========================================================================");
            log.debug("============================ {} ==============================", session.getAttribute(userId));
            log.debug("==========================================================================");
        }

        return true;
    }
}
