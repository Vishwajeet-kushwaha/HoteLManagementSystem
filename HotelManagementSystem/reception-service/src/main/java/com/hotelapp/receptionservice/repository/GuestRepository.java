package com.hotelapp.receptionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelapp.receptionservice.entity.Guest;



public interface GuestRepository extends JpaRepository<Guest, Integer>{

}
