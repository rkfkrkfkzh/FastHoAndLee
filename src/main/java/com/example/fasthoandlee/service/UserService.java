// UserService 코드 수정
package com.example.fasthoandlee.service;

import com.example.fasthoandlee.domain.User;
import com.example.fasthoandlee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 등록
    @Transactional
    public void saveUser(User user) {
        if (userRepository.existsByUserId(user.getUserId())) { // existsByUserId() 메서드 사용
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd())); // 비밀번호 암호화
        userRepository.save(user);
    }

    // 로그인 기능
    public Optional<User> loginUser(String userId, String userPwd) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(userPwd, user.getUserPwd())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    // 모든 회원 조회
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
