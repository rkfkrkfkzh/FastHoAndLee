package com.example.fasthoandlee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // 루트 URL에서 홈 화면으로 매핑
    }

    @GetMapping("/login")
    public String login() {
        return "users/form"; // 로그인 페이지 매핑
    }

}
