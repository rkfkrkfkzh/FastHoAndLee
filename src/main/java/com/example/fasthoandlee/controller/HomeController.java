package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.domain.Room;
import com.example.fasthoandlee.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

    private final RoomService roomService;

    @GetMapping("/home")
    public List<Room> getRecentRooms() {
        return roomService.findAllRooms(); // 최근 객실을 조회하는 서비스 메서드
    }
}
