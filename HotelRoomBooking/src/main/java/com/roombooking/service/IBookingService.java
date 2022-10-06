package com.roombooking.service;

import com.roombooking.exception.RoomNotAvailableException;
import com.roombooking.model.Room;

public interface IBookingService {
	Room booking(String category, String type) throws RoomNotAvailableException;
	void updateAvailablity(Integer roomNumber);
}
