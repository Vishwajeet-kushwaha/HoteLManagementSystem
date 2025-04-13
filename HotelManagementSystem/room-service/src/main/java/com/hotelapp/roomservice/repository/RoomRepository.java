package com.hotelapp.roomservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelapp.roomservice.entity.Room;



public interface RoomRepository extends JpaRepository<Room, Integer>
{

	List<Room> findByRoomAvailIsTrue();

	Optional<Room> findByRoomNo(int roomNo);
	
	List<Room> findRoomsByRoomType(String roomType);
	
}

