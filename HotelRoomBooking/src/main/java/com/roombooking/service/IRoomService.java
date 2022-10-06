package com.roombooking.service;

import java.time.LocalDate;
import java.util.List;

import com.roombooking.exception.CategoryNotFoundException;
import com.roombooking.exception.RoomNotAvailableException;
import com.roombooking.exception.TypeNotFoundException;
import com.roombooking.model.Room;

public interface IRoomService {

	int addRoom(Room room);

	int updateRoom(int roomNumber, double fare);

	int deleteRoom(int roomNumber);

	List<Room> getAllRooms();

	List<Room> getByLessPrice();

	List<Room> getByCategoryAndPrice(String category, double price) throws CategoryNotFoundException;

	List<Room> getByCategory(String category) throws CategoryNotFoundException;

	List<Room> getByRoomType(String roomType, double price) throws TypeNotFoundException;

	List<Room> getByAvailability(LocalDate startDate) throws RoomNotAvailableException;

	List<Room> getByAvailabilityAndType(LocalDate startDate, String category, String type)
			throws RoomNotAvailableException;
}
