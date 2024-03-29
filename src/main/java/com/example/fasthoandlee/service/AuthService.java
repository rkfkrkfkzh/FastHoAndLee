package com.example.fasthoandlee.service;

import com.example.fasthoandlee.config.security.JwtUtil;
import com.example.fasthoandlee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 로그인 기능 및 토큰 생성
    public Optional<String> loginAndGenerateToken(String userId, String userPwd) {
        return userRepository.findByUserId(userId)
                .filter(user -> passwordEncoder.matches(userPwd, user.getUserPwd()))
                .map(user -> jwtUtil.generateToken(userId));
    }
}
