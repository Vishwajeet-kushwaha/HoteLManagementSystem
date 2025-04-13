package com.hotelapp.managerservice.service;

import java.util.List;
import java.util.Optional;

import com.hotelapp.managerservice.entity.Manager;

public interface ManagerService {
	List<Manager> getAllManagers();
	Optional<Manager> getManagerById(Long id);
	Manager createManager(Manager manager);
	Manager updateManager(Long id, Manager updatedManager);
	void deleteManager(Long id);
	
}
