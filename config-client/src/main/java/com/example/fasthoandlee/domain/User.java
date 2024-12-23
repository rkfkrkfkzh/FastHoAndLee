package com.example.fasthoandlee.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String address; // 기본 주소
    private String detailAddress; // 상세 주소
    private String extraAddress; // 참고 항목
    private String postcode; // 우편번호
    private LocalDateTime signUpDate = LocalDateTime.now(); // 회원 가입일, 기본값으로 현재 시간 설정
    private String role = "USER"; // 권한, 기본값으로 "USER" 설정

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Reservation> reservation = new ArrayList<>();

}
