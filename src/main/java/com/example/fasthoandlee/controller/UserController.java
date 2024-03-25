package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.config.security.JwtConfig;
import com.example.fasthoandlee.domain.User;
import com.example.fasthoandlee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtConfig jwtConfig;

    @PostMapping("/login")
    public String login(User user) {
        User users = userService.findByUserIdAndUserPwd(user.getUserId(), user.getUserPwd());
        return jwtConfig.createToken(users.getUserId(), Arrays.asList(users.getUserRole().getValue()));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build(); // 로그아웃 성공 시 200 OK 응답 반환
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
