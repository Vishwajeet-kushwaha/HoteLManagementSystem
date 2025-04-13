package com.hotelapp.managerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelapp.managerservice.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByManagerId(Long managerId);
}
