package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.domain.User;
import com.example.fasthoandlee.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users/list"; // users/list.html 템플릿
    }

    @GetMapping("/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form"; // users/form.html 템플릿
    }

    @PostMapping
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}
