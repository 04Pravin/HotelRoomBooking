
package com.roombooking.service;

import com.roombooking.dao.BookingDaoImpl;
import com.roombooking.dao.IBookingDao;
import com.roombooking.model.Room;

public class BookingServiceImpl implements IBookingService {
	
	IBookingDao bookingDao = new BookingDaoImpl();
	@Override
	public Room booking(String category, String type) {
		
		return bookingDao.booking(category, type);
	}
	/**
	 * @param roomNumber
	 */
	@Override
	public void updateAvailablity(Integer roomNumber) {
		bookingDao.updateAvailablity(roomNumber);
	}

}
