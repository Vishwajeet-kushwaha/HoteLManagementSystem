package com.hotelapp.receptionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelapp.receptionservice.entity.Reservation;
import com.hotelapp.receptionservice.service.ReservationService;



@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addReservation(@RequestBody Reservation reservation)
	{
		String msg=reservationService.addReservation(reservation);
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Reservation> modifyReservation(@RequestBody Reservation reservation)
	{
		Reservation updatedReservation=reservationService.updateReservation(reservation);
		return new ResponseEntity<>(updatedReservation,HttpStatus.OK);
		
	}
	@DeleteMapping("/delete/{reservationId}")
	public ResponseEntity<String> removeReservation(@PathVariable("reservationId") int id)
	{
		String msg=reservationService.deleteReservation(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	@GetMapping("/find/{reservationId}")
	public ResponseEntity<Reservation> fetchByReservationId(@PathVariable("reservationId") int id)
	{
		Reservation reservation=reservationService.getReservationById(id);
		return new ResponseEntity<>(reservation,HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Reservation>> fetchAllReservations()
	{
		List<Reservation> list=reservationService.getAllReservations();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}