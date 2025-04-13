package com.hotelapp.managerservice.service;

import java.util.List;

import com.hotelapp.managerservice.entity.Staff;

public interface StaffService {
    Staff createStaff(Long managerId, Staff staff);
    List<Staff> getAllStaff();
    Staff getStaffById(Long id);
    Staff updateStaff(Long id, Staff updatedStaff);
    void deleteStaff(Long id);
}
