package com.example.fasthoandlee.config;

import com.example.fasthoandlee.domain.Room;
import com.example.fasthoandlee.repository.RoomRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final RoomRepository roomRepository;

    @PostConstruct
    public void loadData() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Room>> typeReference = new TypeReference<List<Room>>() {};
        try (InputStream inputStream = getClass().getResourceAsStream("/static/rooms.json")) {
            if (inputStream != null) {
                List<Room> rooms = mapper.readValue(inputStream, typeReference);
                roomRepository.saveAll(rooms);
                System.out.println("룸 데이터가 로드되어 데이터베이스에 저장되었습니다.");
            } else {
                System.out.println("Rooms.json 파일을 찾을 수 없습니다!\n");
            }
        } catch (IOException e) {
            System.out.println("객실 데이터를 저장할 수 없습니다. " + e.getMessage());
        }
    }
}
