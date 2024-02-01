package com.example.fasthoandlee.service;

import com.example.fasthoandlee.domain.Reservation;
import com.example.fasthoandlee.repository.ReservationRepository;
import com.example.fasthoandlee.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findReservationById(Long id) {
        return reservationRepository.findById(id);
    }
    @Transactional
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Transactional
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

}
