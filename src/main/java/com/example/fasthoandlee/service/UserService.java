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

    // 회원 등록
    @Transactional
    public void saveUser(User user) {
        if (userRepository.findByUserId(user.getUserId()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        userRepository.save(user);
    }

    public Optional<User> loginUser(String userId, String userPwd) {
        return userRepository.findByUserId(userId)
                .filter(user -> user.getUserPwd().equals(userPwd));
    }

    public boolean existsByUserId(String userId) {
        return userRepository.findByUserId(userId).isPresent();
    }

    // 모든 회원 조회
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // id로 회원 조회
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // 회원 삭제
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
