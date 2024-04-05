package com.example.fasthoandlee.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId; // 아이디
    private String userPwd; // 비밀번호
    private String userName; // 이름
    private String email; // 이메일
    private String phoneNumber; // 연락처
    private String address; // 기본 주소
    private String detailAddress; // 상세 주소
    private String extraAddress; // 참고 항목
    private String postcode; // 우편번호
    private LocalDate birthDate; // 생일
    private String gender; // 성별
    private LocalDateTime signUpDate = LocalDateTime.now(); // 회원 가입일, 기본값으로 현재 시간 설정
    private String role = "USER"; // 권한, 기본값으로 "USER" 설정

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reservation> reservation = new ArrayList<>();

}
