package com.example.fasthoandlee.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id") // 추가
    private Room room;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id") // 추가
    private User user;

    private LocalDate checkIn; // 수정: Date -> LocalDate
    private LocalDate checkOut; // 수정: Date -> LocalDate
}
