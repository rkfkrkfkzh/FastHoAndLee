package com.example.fasthoandlee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // 루트 URL에서 홈 화면으로 매핑
    }

}
