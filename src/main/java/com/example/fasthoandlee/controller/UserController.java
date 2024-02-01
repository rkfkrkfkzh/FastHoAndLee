package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.domain.User;
import com.example.fasthoandlee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "users/loginForm";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String userPwd, Model model, HttpSession session) {
        Optional<User> user = userService.loginUser(userId, userPwd);
        if (user.isPresent()) {
            // 세션에 사용자 정보 저장
            session.setAttribute("user", user.get());
            return "redirect:/"; // 로그인 성공 시 홈 페이지로 리다이렉트
        } else {
            // login 실패
            model.addAttribute("loginError", "ID 또는 Password가 잘못되었습니다");
            return "redirect:/users/login"; // 로그인 폼으로 다시 리다이렉트
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 홈 페이지로 리다이렉트
    }


    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/createForm";
    }

    @PostMapping("/new")
    public String createUser(User user, Model model) {
        if (userService.existsByUserId(user.getUserId())) {
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", "이미 존재하는 ID입니다. 다른 ID를 사용해주세요."); // Error message for the user
            return "users/createForm";
        }
        try {
            userService.saveUser(user);
            return "users/loginForm";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users/list"; // users/list.html 템플릿
    }
}
