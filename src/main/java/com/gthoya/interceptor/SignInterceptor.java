package com.gthoya.interceptor;

import com.gthoya.application.sign.model.User;
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
        HttpSession session = request.getSession();

        if (HttpSessionUtils.getUserFromSession(session) != null) {
            log.debug("================================ signInterceptor =================================");
            log.debug("==================== {}, {} ======================", ((User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY)).getUserId() , request.getRequestURL());
            log.debug("==================================================================================");
        }

        return true;
    }
}
