package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.domain.User;
import com.example.fasthoandlee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.isAuthenticated()) {
//            return "redirect:/"; // 이미 로그인되어 있는 경우 홈페이지로 리다이렉트
//        }
        return "users/loginForm";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String userPwd, HttpSession session) {
        if (userService.loginUser(userId, userPwd).isPresent()) {
            session.setAttribute("user", userId.getClass()); // 세션에 사용자 정보 저장
            return "redirect:/"; // 로그인 성공 시 홈 페이지로 리다이렉트
        } else {
            return "redirect:/users/login?error"; // 로그인 실패 시 로그인 페이지로 다시 리다이렉트하고 오류 메시지 플래그 전달
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/users/login"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/createForm";
    }

    @PostMapping("/new")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users/login"; // 회원 등록 후 로그인 페이지로 리다이렉트
    }

    @GetMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users/list"; // 사용자 목록 템플릿으로 이동
    }
}
