package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.domain.Room;
import com.example.fasthoandlee.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RoomService roomService;

    @GetMapping("/")
    public String home(Model model) {
        List<Room> recentRooms = roomService.findAllRooms(); // 가정: 최근 객실을 조회하는 서비스 메서드
        model.addAttribute("recentRooms", recentRooms);
        return "home"; // 루트 URL에서 홈 화면으로 매핑
    }

}
