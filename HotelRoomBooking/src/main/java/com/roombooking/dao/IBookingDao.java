package com.roombooking.dao;

import com.roombooking.model.Room;

public interface IBookingDao {
	
	Room booking(String category, String type);
	
	void updateAvailablity(Integer roomNumber);
}
