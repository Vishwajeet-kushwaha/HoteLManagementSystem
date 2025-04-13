package com.hotelapp.roomservice.service;

import java.util.List;

import com.hotelapp.roomservice.entity.Room;

public interface RoomService {
	Room addRoom(Room room);

	Room updateRoom(Room room);

	String deleteRoom(int id);

	List<Room> getRoomsAvail();
	
	List<Room> getAllRooms();
	
	Room findByRoomId(int id);
	
	List<Room> findByRoomType(String roomType);
	
	Room getByRoomNo(int number);
	
	Room checkOut(int roomNo);

}
