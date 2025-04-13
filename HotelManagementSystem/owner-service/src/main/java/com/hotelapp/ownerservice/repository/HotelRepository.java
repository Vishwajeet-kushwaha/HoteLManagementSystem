package com.hotelapp.ownerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelapp.ownerservice.entity.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
