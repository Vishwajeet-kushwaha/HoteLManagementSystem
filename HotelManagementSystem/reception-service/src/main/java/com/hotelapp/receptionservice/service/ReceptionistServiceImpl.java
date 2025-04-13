package com.hotelapp.receptionservice.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelapp.receptionservice.entity.Receptionist;
import com.hotelapp.receptionservice.repository.ReceptionistRepository;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {
	
	
	@Autowired
	private ReceptionistRepository receptionistRepository;
	
	
	@Override
	public Receptionist addReceptionist(Receptionist receptionist) {
		// TODO Auto-generated method stub
		Receptionist temp = receptionistRepository.save(receptionist);
		return temp;
	}

	
	@Override
	public List<Receptionist> getAllReceptionists() {
		// TODO Auto-generated method stub
		return receptionistRepository.findAll();
		
	}

}
