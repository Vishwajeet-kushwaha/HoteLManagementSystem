package com.hotelapp.receptionservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotelapp.receptionservice.entity.Reservation;
import com.hotelapp.receptionservice.repository.ReservationRepository;
import com.hotelapp.roomservice.entity.Room;



@Service
public class ReservationServiceImpl implements ReservationService{
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public String addReservation(Reservation reservation) {
		
		// TODO Auto-generated method stub
		Room room = restTemplate.getForObject("http://localhost:8082/room/" + reservation.getRoomNo(), Room.class);

        if (room != null && room.isRoomAvail()) {
            reservationRepository.save(reservation);

            // Mark room as booked 
            room.setRoomAvail(false);
            restTemplate.put("http://localhost:8082/room/update", room);

            return "Room " + room.getRoomNo() + " is booked successfully";
        }
        return "Room already booked or not found";
    }

	@Override
	public Reservation updateReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return reservationRepository.save(reservation);
	}

	@Override
	public String deleteReservation(int id) {
		// TODO Auto-generated method stub
		reservationRepository.deleteById(id);
        return "Reservation deleted successfully with ID " + id;
	}

	@Override
	public Reservation getReservationById(int id) {
		// TODO Auto-generated method stub
		return reservationRepository.findById(id).orElse(null);
	}

	@Override
	public List<Reservation> getAllReservations() {
		// TODO Auto-generated method stub
		return reservationRepository.findAll();
	}
	

}
