package com.hotelapp.receptionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelapp.receptionservice.entity.Receptionist;
import com.hotelapp.receptionservice.service.ReceptionistService;
@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {

	@Autowired
	private ReceptionistService receptionistService;
	

	
	@GetMapping("/all")
	public ResponseEntity<List<Receptionist>> getAllReceptionist() {
		List<Receptionist> receptionists = receptionistService.getAllReceptionists();
		return new ResponseEntity<>(receptionists,HttpStatus.OK);
	}
}
