package com.example.fasthoandlee.controller.requestDTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String userId;
    private String userPwd;
}