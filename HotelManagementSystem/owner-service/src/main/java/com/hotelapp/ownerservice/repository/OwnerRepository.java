package com.hotelapp.ownerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelapp.ownerservice.entity.Owner;


public interface OwnerRepository extends JpaRepository<Owner, Long> {
}