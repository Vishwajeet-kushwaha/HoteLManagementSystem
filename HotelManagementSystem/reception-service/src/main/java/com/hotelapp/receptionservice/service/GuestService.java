package com.hotelapp.receptionservice.service;

import java.util.List;

import com.hotelapp.receptionservice.entity.Guest;

public interface GuestService {


	Guest addGuest(Guest guest);

	Guest updateGuest(Guest guest);

	Guest getByGuestId(int id);

	List<Guest> getAllGuests();

	String deleteGuest(int id);

}
