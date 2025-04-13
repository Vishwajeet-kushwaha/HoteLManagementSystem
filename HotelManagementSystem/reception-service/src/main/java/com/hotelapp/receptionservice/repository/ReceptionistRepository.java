package com.hotelapp.receptionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelapp.receptionservice.entity.Receptionist;


public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer>
{
	
}
