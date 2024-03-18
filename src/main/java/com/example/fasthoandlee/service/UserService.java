package com.example.fasthoandlee.service;

import com.example.fasthoandlee.domain.User;
import com.example.fasthoandlee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 등록
    @Transactional
    public void saveUser(User user) {
        if (userRepository.findByUserId(user.getUserId()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd())); // 비밀번호 암호화

        userRepository.save(user);
    }

    public Optional<User> loginUser(String userId, String userPwd) {
        return userRepository.findByUserId(userId)
                .filter(user -> user.getUserPwd().equals(userPwd));
    }

    // 모든 회원 조회
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
