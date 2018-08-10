package com.gthoya.util;

import com.gthoya.application.sign.model.User;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "user";

    public static boolean isLoginUser(HttpSession session) {
        if (session.getAttribute(USER_SESSION_KEY) == null) {
            return false;
        }

        return true;
    }

    public static User getUserFromSession(HttpSession session) {
        if (!isLoginUser(session)) {
            return null;
        }

        return (User) session.getAttribute(USER_SESSION_KEY);
    }
}
