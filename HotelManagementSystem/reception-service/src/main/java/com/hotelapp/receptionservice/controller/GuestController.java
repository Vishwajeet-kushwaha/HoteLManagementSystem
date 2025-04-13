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

import com.hotelapp.receptionservice.entity.Guest;
import com.hotelapp.receptionservice.service.GuestService;
@RestController
@RequestMapping("/guest")
public class GuestController {

	@Autowired
	private GuestService guestService;
		
		@PostMapping("/add")
		public ResponseEntity<Guest> saveGuest(@RequestBody Guest guest)
		{
			Guest savedGuest=guestService.addGuest(guest);
			return new ResponseEntity<>(savedGuest,HttpStatus.CREATED);
		}
		@GetMapping("/all")
		public ResponseEntity<List<Guest>> fetchAllGuests()
		{
			List<Guest> guests=guestService.getAllGuests();
			return new ResponseEntity<>(guests,HttpStatus.OK);
		}
		@GetMapping("/find/{guestId}")
		public ResponseEntity<Guest> fetchByGuestId(@PathVariable("guestId") int id)
		{
			Guest guest=guestService.getByGuestId(id);
			return new ResponseEntity<>(guest,HttpStatus.OK);
		}
		@PutMapping("/update")
		public ResponseEntity<Guest> modifyGuest(@RequestBody Guest guest)
		{
			Guest updatedGuest=guestService.updateGuest(guest);
			return new ResponseEntity<>(updatedGuest,HttpStatus.OK);
		}
		@DeleteMapping("/delete/{guestId}")
		public ResponseEntity<String> removeGuest(@PathVariable("guestId") int id)
		{
			String msg=guestService.deleteGuest(id);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
}
