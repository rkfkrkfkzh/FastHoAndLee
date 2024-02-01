package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.domain.Room;
import com.example.fasthoandlee.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/list")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomService.findAllRooms());
        return "rooms/list";
    }

    @GetMapping("/new")
    public String RoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "rooms/createForm";
    }

    @PostMapping("/new")
    public String createRoom(Room room) {
        try {
            roomService.saveRoom(room);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}
