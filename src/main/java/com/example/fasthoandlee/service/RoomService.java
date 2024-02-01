package com.example.fasthoandlee.service;

import com.example.fasthoandlee.domain.Room;
import com.example.fasthoandlee.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
