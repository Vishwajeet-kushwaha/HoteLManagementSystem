package com.hotelapp.managerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelapp.managerservice.entity.Manager;
import com.hotelapp.managerservice.repository.ManagerRepository;

@Service
public class ManagerServiceImpl implements ManagerService {  

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public Optional<Manager> getManagerById(Long id) {
        return managerRepository.findById(id);
    }

    @Override
    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager updateManager(Long id, Manager updatedManager) {
        return managerRepository.findById(id)
                .map(manager -> {
                    manager.setName(updatedManager.getName());
                    manager.setEmail(updatedManager.getEmail());
                    manager.setPhone(updatedManager.getPhone());
                    manager.setDepartment(updatedManager.getDepartment());
                    return managerRepository.save(manager);
                }).orElseThrow(() -> new RuntimeException("Manager not found"));
    }

    @Override
    public void deleteManager(Long id) {
        if (!managerRepository.existsById(id)) {
            throw new RuntimeException("Manager not found");
        }
        managerRepository.deleteById(id);
    }
}
