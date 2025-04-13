package com.hotel.inventory.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.hotel.inventory.entity.Inventory;
import com.hotel.inventory.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InventoryServiceTests {

    @Mock
    private InventoryRepository repository;

    @InjectMocks
    private InventoryServiceImpl service;

    private Inventory inventory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        inventory = new Inventory();
        inventory.setId(1L);
        inventory.setName("Water Bottle");
        inventory.setType("Beverage");
        inventory.setAvailable(true);
    }

    @Test
    public void addInventoryTest() {
        when(repository.save(any(Inventory.class))).thenReturn(inventory);

        Inventory saved = service.addInventory(inventory);

        assertNotNull(saved);
        assertEquals("Water Bottle", saved.getName());
        verify(repository, times(1)).save(inventory);
    }

    @Test
    public void getInventoryByIdTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(inventory));

        Inventory result = service.getInventoryById(1L);

        assertNotNull(result);
        assertEquals("Water Bottle", result.getName());
        verify(repository).findById(1L);
    }

    @Test
    public void getInventoryById_NotFoundTest() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.getInventoryById(2L);
        });

        assertEquals("Inventory with id 2 not found", ex.getMessage());
    }

    @Test
    public void updateInventoryTest() {
        Inventory updated = new Inventory();
        updated.setName("Updated Bottle");
        updated.setType("Drink");
        updated.setAvailable(false);

        when(repository.findById(1L)).thenReturn(Optional.of(inventory));
        when(repository.save(any(Inventory.class))).thenReturn(updated);

        Inventory result = service.updateInventory(1L, updated);

        assertEquals("Updated Bottle", result.getName());
        assertFalse(result.isAvailable());
        verify(repository).save(any(Inventory.class));
    }

    @Test
    public void updateInventory_NotFoundTest() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        Inventory updated = new Inventory();

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.updateInventory(2L, updated);
        });

        assertEquals("Inventory with id 2 not found", ex.getMessage());
    }

    @Test
    public void deleteInventoryTest() {
    	when(repository.existsById(1L)).thenReturn(true); 
        doNothing().when(repository).deleteById(1L);

        service.deleteInventory(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void getAllInventoryTest() {
        List<Inventory> mockList = Arrays.asList(inventory);
        when(repository.findAll()).thenReturn(mockList);

        List<Inventory> result = service.getAllInventory();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }
}
