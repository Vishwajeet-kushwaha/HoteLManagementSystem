package com.hotel.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

}
