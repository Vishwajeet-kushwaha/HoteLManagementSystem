package com.hotelapp.roomservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelapp.roomservice.entity.Room;
import com.hotelapp.roomservice.exception.RoomNotFoundException;
import com.hotelapp.roomservice.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Room room) {
        if (roomRepository.existsById(room.getRoomId())) {
            return roomRepository.save(room);
        }
        throw new RoomNotFoundException("Room not found with ID " + room.getRoomId());
    }

    @Override
    public String deleteRoom(int id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return "Room deleted successfully with Id " + id;
        }
        throw new RoomNotFoundException("Room not found with Id " + id);
    }

    @Override
    public List<Room> getRoomsAvail() {
        List<Room> availableRooms = roomRepository.findByRoomAvailIsTrue();
        if (availableRooms.isEmpty()) {
            throw new RoomNotFoundException("No available rooms found");
        }
        return availableRooms;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> allRooms = roomRepository.findAll();
        if (allRooms.isEmpty()) {
            throw new RoomNotFoundException("No rooms found in the system");
        }
        return allRooms;
    }

    @Override
    public Room findByRoomId(int id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with ID " + id));
    }

    @Override
    public List<Room> findByRoomType(String roomType) {
        List<Room> rooms = roomRepository.findRoomsByRoomType(roomType);
        if (rooms.isEmpty()) {
            throw new RoomNotFoundException("No rooms found with type: " + roomType);
        }
        return rooms;
    }

    @Override
    public Room getByRoomNo(int roomNo) {
        return roomRepository.findByRoomNo(roomNo)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with number: " + roomNo));
    }

    public Room checkOut(int roomNo) {
        Room foundRoom = roomRepository.findByRoomNo(roomNo)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with number: " + roomNo));
        foundRoom.setRoomAvail(true);
        return roomRepository.save(foundRoom);
    }
}
