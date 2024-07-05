package com.example.fasthoandlee.controller.requestDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationRequest {
    private Long roomId;
    private Long userId;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
