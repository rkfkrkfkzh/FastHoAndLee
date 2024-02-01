package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.domain.Room;
import com.example.fasthoandlee.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomService.findAllRooms());
        return "rooms/list"; // rooms/list.html 템플릿을 반환
    }

    @GetMapping("/new")
    public String showRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "rooms/form"; // rooms/form.html 템플릿을 반환
    }

    @PostMapping
    public String createRoom(Room room) {
        roomService.saveRoom(room);
        return "redirect:/rooms"; // 저장 후 객실 목록으로 리다이렉트
    }

    @GetMapping("/{id}")
    public String getRoomById(@PathVariable Long id, Model model) {
        Room room = roomService.findRoomById(id).orElse(null);
        model.addAttribute("room", room);
        return "rooms/detail"; // rooms/detail.html 템플릿을 반환
    }
}
