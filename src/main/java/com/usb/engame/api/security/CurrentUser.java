package com.usb.engame.api.security;

import jakarta.servlet.http.HttpServletRequest;

public class CurrentUser {

    private CurrentUser() {
    }

    public static Long require(HttpServletRequest request) {
        Object userId = request.getAttribute(JwtAuthFilter.USER_ID_ATTRIBUTE);
        if (userId == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return (Long) userId;
    }
}
