package com.hotelapp.receptionservice.service;

import java.util.List;

import com.hotelapp.receptionservice.entity.Receptionist;


public interface ReceptionistService {
	
	Receptionist addReceptionist(Receptionist receptionist);
	
	List<Receptionist> getAllReceptionists();

}
