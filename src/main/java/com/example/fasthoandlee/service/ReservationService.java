package com.example.fasthoandlee.service;

import com.example.fasthoandlee.domain.Reservation;
import com.example.fasthoandlee.repository.ReservationRepository;
import com.example.fasthoandlee.repository.RoomRepository;
import com.example.fasthoandlee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    // 예약 등록
    @Transactional
    public Reservation reserveRoom(Reservation reservation) {
        reservation.setRoom(roomRepository.findById(reservation.getRoom().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid room Id")));
        reservation.setUser(userRepository.findById(reservation.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid user Id")));
        reservation.setCheckIn(reservation.getCheckIn());
        reservation.setCheckOut(reservation.getCheckOut());
        return reservationRepository.save(reservation);
    }

    // 사용자 ID로 예약 조회
    public List<Reservation> findReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
}
