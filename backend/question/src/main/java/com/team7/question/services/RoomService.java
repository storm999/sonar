package com.team7.question.services;

import com.team7.question.model.Room;
import com.team7.question.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
       return roomRepository.findAll();
    }
    public Room addRoom(Room room) {
      return roomRepository.save(room);
    }

    public Room getRoomById(int id) {
      return  roomRepository.findById(id).get();
    }
}
