package com.hotelapp.roomservice.controller;

import com.hotelapp.roomservice.entity.Room;
import com.hotelapp.roomservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Only ADMIN can add rooms
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
        Room savedRoom = roomService.addRoom(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    // Only ADMIN can update rooms
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Room> modifyRoom(@RequestBody Room room) {
        Room updatedRoom = roomService.updateRoom(room);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    // USER and ADMIN can access this
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/find/{roomId}")
    public ResponseEntity<Room> getByRoomId(@PathVariable("roomId") int id) {
        Room room = roomService.findByRoomId(id);
        return room != null ? new ResponseEntity<>(room, HttpStatus.OK) 
                            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // USER and ADMIN can view all
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Room>> fetchAllRooms() {
        List<Room> list = roomService.getAllRooms();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Only ADMIN can delete rooms
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<String> removeRoom(@PathVariable("roomId") int id) {
        String msg = roomService.deleteRoom(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
