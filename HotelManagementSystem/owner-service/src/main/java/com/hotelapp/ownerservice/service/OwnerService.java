package com.hotelapp.ownerservice.service;

import com.hotelapp.ownerservice.entity.Owner;
import java.util.List;
import java.util.Optional;

public interface OwnerService {
    List<Owner> getAllOwners();
    Optional<Owner> getOwnerById(Long id);
    Owner addOwner(Owner owner);
    void deleteOwner(Long id);
   
  //  void updateBookingsAndRevenue(Long id, int roomsBooked, double revenue);
}
