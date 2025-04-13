package com.hotel.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.inventory.entity.Inventory;
import com.hotel.inventory.exception.InventoryNotFoundException;
import com.hotel.inventory.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Inventory addInventory(Inventory inventory) {
        return repository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existing = repository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory with id " + id + " not found"));

        existing.setType(inventory.getType());
        existing.setName(inventory.getName());
        existing.setAvailable(inventory.isAvailable());

        return repository.save(existing);
    }

    @Override
    public void deleteInventory(Long id) {
        if (!repository.existsById(id)) {
            throw new InventoryNotFoundException("Inventory with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory with id " + id + " not found"));
    }

    @Override
    public List<Inventory> getAllInventory() {
        return repository.findAll();
    }
}
