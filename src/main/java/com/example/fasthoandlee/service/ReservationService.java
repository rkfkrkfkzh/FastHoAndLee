package com.example.fasthoandlee.service;

import com.example.fasthoandlee.domain.Reservation;
import com.example.fasthoandlee.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;

    // 예약 등록
    @Transactional
    public void createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    // 사용자 ID로 예약 조회
    public List<Reservation> findReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
}
