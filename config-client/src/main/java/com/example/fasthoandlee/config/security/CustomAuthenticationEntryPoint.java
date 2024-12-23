package com.example.fasthoandlee.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // 여기서는 예시로 401 상태 코드와 함께 로그인 페이지 URL을 응답 헤더에 추가합니다.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("Location", "/login");
    }
}
