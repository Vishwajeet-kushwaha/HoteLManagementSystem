package com.hotelapp.receptionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelapp.receptionservice.entity.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
