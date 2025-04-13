package com.hotelapp.roomservice;



import com.hotelapp.roomservice.entity.Room;
import com.hotelapp.roomservice.exception.RoomNotFoundException;
import com.hotelapp.roomservice.repository.RoomRepository;
import com.hotelapp.roomservice.service.RoomServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceImplTest {

    @InjectMocks
    private RoomServiceImpl roomService;

    @Mock
    private RoomRepository roomRepository;

    private Room testRoom;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testRoom = new Room();
        testRoom.setRoomId(1);
        testRoom.setRoomNo(101);
        testRoom.setRoomType("Deluxe");
        testRoom.setRoomAvail(true);
    }

    @Test
    void testAddRoom() {
        when(roomRepository.save(testRoom)).thenReturn(testRoom);
        Room saved = roomService.addRoom(testRoom);
        assertEquals(testRoom, saved);
    }

    @Test
    void testUpdateRoomSuccess() {
        when(roomRepository.existsById(1)).thenReturn(true);
        when(roomRepository.save(testRoom)).thenReturn(testRoom);
        Room updated = roomService.updateRoom(testRoom);
        assertEquals(testRoom, updated);
    }

    @Test
    void testUpdateRoomFailure() {
        when(roomRepository.existsById(1)).thenReturn(false);
        assertThrows(RoomNotFoundException.class, () -> roomService.updateRoom(testRoom));
    }

    @Test
    void testDeleteRoomSuccess() {
        when(roomRepository.existsById(1)).thenReturn(true);
        doNothing().when(roomRepository).deleteById(1);
        String response = roomService.deleteRoom(1);
        assertEquals("Room deleted successfully with Id 1", response);
    }

    @Test
    void testDeleteRoomFailure() {
        when(roomRepository.existsById(1)).thenReturn(false);
        assertThrows(RoomNotFoundException.class, () -> roomService.deleteRoom(1));
    }

    @Test
    void testGetRoomsAvailSuccess() {
        when(roomRepository.findByRoomAvailIsTrue()).thenReturn(List.of(testRoom));
        List<Room> available = roomService.getRoomsAvail();
        assertEquals(1, available.size());
    }

    @Test
    void testGetRoomsAvailEmpty() {
        when(roomRepository.findByRoomAvailIsTrue()).thenReturn(Collections.emptyList());
        assertThrows(RoomNotFoundException.class, () -> roomService.getRoomsAvail());
    }

    @Test
    void testFindByRoomIdSuccess() {
        when(roomRepository.findById(1)).thenReturn(Optional.of(testRoom));
        Room found = roomService.findByRoomId(1);
        assertEquals(101, found.getRoomNo());
    }

    @Test
    void testFindByRoomIdFailure() {
        when(roomRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(RoomNotFoundException.class, () -> roomService.findByRoomId(1));
    }

    @Test
    void testGetByRoomNoSuccess() {
        when(roomRepository.findByRoomNo(101)).thenReturn(Optional.of(testRoom));
        Room found = roomService.getByRoomNo(101);
        assertEquals("Deluxe", found.getRoomType());
    }

    @Test
    void testGetByRoomNoFailure() {
        when(roomRepository.findByRoomNo(101)).thenReturn(Optional.empty());
        assertThrows(RoomNotFoundException.class, () -> roomService.getByRoomNo(101));
    }

    @Test
    void testCheckOutFailure() {
        when(roomRepository.findByRoomNo(101)).thenReturn(Optional.empty());
        assertThrows(RoomNotFoundException.class, () -> roomService.checkOut(101));
    }
}
