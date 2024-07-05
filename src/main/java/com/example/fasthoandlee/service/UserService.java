package com.example.fasthoandlee.service;

import com.example.fasthoandlee.domain.User;
import com.example.fasthoandlee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 등록
    @Transactional
    public void saveUser(User user) {
        if (userRepository.existsByUserId(user.getUserId())) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));
        userRepository.save(user);
    }

    // 모든 회원 조회
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
