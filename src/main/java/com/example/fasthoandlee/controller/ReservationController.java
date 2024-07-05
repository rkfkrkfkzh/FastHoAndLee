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

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest request) {
        try {
            Reservation reservation = reservationService.reserveRoom(
                    request.getRoomId(),
                    request.getUserId(),
                    request.getCheckIn(),
                    request.getCheckOut());
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("예약 생성 중 오류가 발생했습니다.");
        }
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
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7); // "Bearer " 부분 제거
            Long userId = jwtUtil.extractUserId(jwt);
            reservationService.cancelReservation(id, userId);
            return ResponseEntity.ok("예약이 취소되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("예약 취소 중 오류가 발생했습니다.");
        }
    }
}
