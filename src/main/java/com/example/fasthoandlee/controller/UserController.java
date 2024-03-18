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
            // 로그인 실패
            model.addAttribute("loginError", "ID 또는 Password가 잘못되었습니다");
            return "users/loginForm"; // 로그인 폼으로 다시 리다이렉트하지 않고, 에러 메시지와 함께 로그인 폼을 다시 보여줍니다.
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
        try {
            userService.saveUser(user);
            return "redirect:/users/login"; // 회원 등록 성공 시 로그인 폼으로 리다이렉트
        } catch (IllegalStateException e) {
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", e.getMessage()); // 예외 메시지를 errorMessage로 설정
            return "users/createForm"; // 회원 등록 실패 시, 회원 등록 폼으로 돌아갑니다.
        }
    }

    @GetMapping("/list")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users/list"; // users/list.html 템플릿
    }
}
