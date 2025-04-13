package com.hotel.inventory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.hotel.inventory.entity.Inventory;
import com.hotel.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    // Constructor Injection 
    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // Create new inventory item
    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return service.addInventory(inventory);
    }

    // Update inventory item by ID
    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        return service.updateInventory(id, inventory);
    }

    // Delete inventory item by ID
    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        service.deleteInventory(id);
    }

    // Get inventory item by ID
    @GetMapping("/{id}")
    public Inventory getInventory(@PathVariable Long id) {
        return service.getInventoryById(id);
    }

    // Get all inventory items
    @GetMapping
    public List<Inventory> getAllInventory() {
        return service.getAllInventory();
    }
}
