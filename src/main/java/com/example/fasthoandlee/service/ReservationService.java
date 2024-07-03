package com.example.fasthoandlee.service;

import com.example.fasthoandlee.domain.Reservation;
import com.example.fasthoandlee.domain.Room;
import com.example.fasthoandlee.domain.User;
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

    @Transactional
    public Reservation reserveRoom(Long roomId, Long userId, LocalDate checkIn, LocalDate checkOut) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalStateException("유효하지 않은 객실 ID입니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("유효하지 않은 고객 ID입니다."));

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);

        return reservationRepository.save(reservation);
    }

    // 사용자 ID로 예약 조회
    public List<Reservation> findReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
}
