package com.example.fasthoandlee.config.security;

import java.io.IOException;
import javax.servlet.ServletException;

import com.example.fasthoandlee.domain.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        logger.info("승인 헤더 : {}", token);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            logger.info("추출된 토큰 : {}", token);

            try {
                // 사용자 이름 대신 사용자 ID를 사용하도록 수정
                Long userId = jwtUtil.extractUserId(token);
                String userName = jwtUtil.getUsernameFromToken(token);
                logger.info("토큰의 사용자 ID : {}", userId);
                logger.info("토큰의 사용자 이름 : {}", userName);

                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 사용자 객체 생성 (User 클래스에 맞게 수정 필요)
                    User user = new User();
                    user.setId(userId);
                    user.setUserId(jwtUtil.getUserIdFromToken(token));
                    user.setUserName(jwtUtil.getUsernameFromToken(token));

                    // 토큰 유효성 검사
                    if (jwtUtil.validateToken(token, user)) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (Exception e) {
                logger.error("토큰 처리 오류 : {}", e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

}
