package com.usb.engame.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Bearer 토큰이 있으면 userId를 request attribute로 세팅만 해두고,
 * 실제로 인증이 필요한지 여부는 각 컨트롤러가 CurrentUser로 판단한다.
 * (토큰이 없거나 유효하지 않아도 여기서 막지 않음 - 공개 API도 있기 때문)
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String USER_ID_ATTRIBUTE = "userId";

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Long userId = jwtService.parseUserId(token);
                request.setAttribute(USER_ID_ATTRIBUTE, userId);
            } catch (Exception e) {
                // 토큰이 잘못됐으면 그냥 인증 안 된 상태로 진행 (컨트롤러에서 401 처리)
            }
        }
        chain.doFilter(request, response);
    }
}
