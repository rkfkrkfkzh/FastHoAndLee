package com.example.fasthoandlee.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String imageUrl = "/images/room.png"; // 기본 이미지 경로

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Reservation> reservation = new ArrayList<>();
}