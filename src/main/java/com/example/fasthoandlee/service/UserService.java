package com.example.fasthoandlee.service;

import com.example.fasthoandlee.domain.User;
import com.example.fasthoandlee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 모든 회원 조회
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // id로 회원 조회
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // 회원 등록
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // 회원 삭제
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
