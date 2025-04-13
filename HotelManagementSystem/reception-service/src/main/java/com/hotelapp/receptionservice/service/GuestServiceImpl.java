package com.hotelapp.receptionservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelapp.receptionservice.entity.Guest;
import com.hotelapp.receptionservice.repository.GuestRepository;


@Service
public class GuestServiceImpl implements GuestService{

	@Autowired
    private GuestRepository guestRepository;
	
	@Override
	public Guest addGuest(Guest guest) {
		// TODO Auto-generated method stub
		validateGuestDetails(guest);
        return guestRepository.save(guest);
	}

	@Override
	public Guest getByGuestId(int id) {
		// TODO Auto-generated method stub
		return guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with ID " + id));
	}

	@Override
	public Guest updateGuest(Guest guest) {
		// TODO Auto-generated method stub
		if (!guestRepository.existsById(guest.getGuestId())) {
            throw new RuntimeException("Guest not found with ID " + guest.getGuestId());
        }
        validateGuestDetails(guest);
        return guestRepository.save(guest);

	}

	@Override
	public List<Guest> getAllGuests() {
		// TODO Auto-generated method stub
		List<Guest> guests = guestRepository.findAll();
        if (guests.isEmpty()) {
            throw new RuntimeException("No guests are present in the database");
        }
        return guests;
	}

	@Override
	public String deleteGuest(int id) {
		// TODO Auto-generated method stub
		if (!guestRepository.existsById(id)) {
            throw new RuntimeException("Guest not found with ID " + id);
        }
        guestRepository.deleteById(id);
        return "Successfully deleted guest with ID " + id;
	}
	private void validateGuestDetails(Guest guest) {
        if (!guest.getGuestMobile().matches("^[6-9]{1}[0-9]{9}$")) {
            throw new RuntimeException("Invalid mobile number");
        }
        if (!guest.getGuestEmail().matches("^[A-Za-z]*[0-9]*[@]{1}[a-z]*[.]{1}[a-z]*$")) {
            throw new RuntimeException("Invalid email address");
        }
        if (!guest.getGuestGovtId().matches("^[2-9]{1}[0-9]{11}$")) {
            throw new RuntimeException("Invalid Aadhar number");
        }
    }

}
