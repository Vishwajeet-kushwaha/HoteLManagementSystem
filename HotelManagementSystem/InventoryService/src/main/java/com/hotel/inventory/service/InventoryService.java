package com.hotel.inventory.service;

import java.util.List;

import com.hotel.inventory.entity.Inventory;



public interface InventoryService {
    Inventory addInventory(Inventory inventory);  
    Inventory updateInventory(Long id, Inventory inventory);
    void deleteInventory(Long id);
    Inventory getInventoryById(Long id);
    List<Inventory> getAllInventory();
}