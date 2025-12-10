package com.wuyunbin.quadra.admin.adapter.in.web.intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import com.wuyunbin.quadra.admin.application.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired(required = false)
    private JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("Request: {}", requestURI);

        String header = request.getHeader("Authorization");
        if (header == null || header.isBlank()) {
            log.info("Authorization header missing");
            return false;
        }

        String token = header.startsWith("Bearer ") ? header.substring(7) : header;
        try {
            if (jwtProvider != null) {
                jwtProvider.parseToken(token);
                log.info("Authorization token parsed");
            } else {
                log.info("JwtProvider not available, skipping token parse");
            }
        } catch (Exception e) {
            log.warn("Invalid Authorization token: {}", e.getMessage());
        }
        return true;
    }
}
