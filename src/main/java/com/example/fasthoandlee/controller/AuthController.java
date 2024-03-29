package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.domain.User;
import com.example.fasthoandlee.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return authService.loginAndGenerateToken(user.getUserId(), user.getUserPwd())
                .map(token -> ResponseEntity.ok().body(Map.of("token", token))) // 성공 시, 토큰을 포함한 JSON 객체 반환
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인 실패!!! 다시 입력해주세요")));
    }


    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        // 로그아웃 처리는 일반적으로 서버 측에서 세션을 무효화하거나 클라이언트에서 토큰을 삭제하는 방식으로 이루어집니다.
        // JWT를 사용하는 경우 대부분의 로그아웃 처리는 클라이언트 측에서 이루어집니다.
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}
