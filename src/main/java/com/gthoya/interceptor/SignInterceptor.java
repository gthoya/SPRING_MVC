package com.gthoya.interceptor;

import com.gthoya.util.HttpSessionUtils;
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
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if (session.getAttribute(HttpSessionUtils.USER_SESSION_KEY) != null) {
            log.debug("==========================================================================");
            log.debug("============================ {} ==============================", session.getAttribute(HttpSessionUtils.USER_SESSION_KEY));
            log.debug("==========================================================================");
        } else if (userId != null && password != null) {
            log.debug("==========================================================================");
            log.debug("============================ {} ==============================", "NO SESSION");
            log.debug("==========================================================================");
        }

        return true;
    }
}
