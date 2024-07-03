package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.config.security.JwtUtil;
import com.example.fasthoandlee.controller.requestDTO.ReservationRequest;
import com.example.fasthoandlee.domain.Reservation;
import com.example.fasthoandlee.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public Reservation createReservation(@RequestBody ReservationRequest request) {
        return reservationService.reserveRoom(
                request.getRoomId(),
                request.getUserId(),
                request.getCheckIn(),
                request.getCheckOut());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7); // "Bearer " 부분 제거
            Long userId = jwtUtil.extractUserId(jwt);
            List<Reservation> reservations = reservationService.findReservationsByUserId(userId);
            if (reservations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reservations);
            }
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
