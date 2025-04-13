package com.hotelapp.managerservice.service;

import com.hotelapp.managerservice.entity.Manager;
import com.hotelapp.managerservice.entity.Staff;
import com.hotelapp.managerservice.repository.ManagerRepository;
import com.hotelapp.managerservice.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Staff createStaff(Long managerId, Staff staff) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        staff.setManager(manager);
        return staffRepository.save(staff);
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff member not found"));
    }

    @Override
    public Staff updateStaff(Long id, Staff updatedStaff) {
        return staffRepository.findById(id)
                .map(staff -> {
                    staff.setName(updatedStaff.getName());
                    staff.setRole(updatedStaff.getRole());
                    staff.setEmail(updatedStaff.getEmail());
                    return staffRepository.save(staff);
                }).orElseThrow(() -> new RuntimeException("Staff member not found"));
    }

    @Override
    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new RuntimeException("Staff member not found");
        }
        staffRepository.deleteById(id);
    }
}
