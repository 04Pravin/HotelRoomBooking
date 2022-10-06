package com.roombooking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.roombooking.dao.IRoomDao;
import com.roombooking.dao.RoomDaoImpl;
import com.roombooking.exception.CategoryNotFoundException;
import com.roombooking.exception.RoomNotAvailableException;
import com.roombooking.exception.TypeNotFoundException;
import com.roombooking.model.Room;

public class RoomServiceImpl implements IRoomService {
	IRoomDao roomDao = new RoomDaoImpl();

	@Override
	public int addRoom(Room room) {
		return roomDao.addRoom(room);

	}

	@Override
	public int updateRoom(int roomNumber, double fare) {
		return roomDao.updateRoom(roomNumber, fare);

	}

	@Override
	public int deleteRoom(int roomNumber) {
		return roomDao.deleteRoom(roomNumber);

	}

	@Override
	public List<Room> getAllRooms() {
		List<Room> rooms = new ArrayList<>();
		rooms = roomDao.findAllRooms().stream()
				.sorted((roomOne, roomTwo) -> roomOne.getRoomNumber().compareTo(roomTwo.getRoomNumber()))
				.collect(Collectors.toList());
		return rooms;
	}

	@Override
	public List<Room> getByCategoryAndPrice(String category, double price) throws CategoryNotFoundException {
		List<Room> rooms = roomDao.findByCategoryAndPrice(category, price);
		rooms = rooms.stream().sorted((roomOne, roomTwo) -> roomOne.getRoomNumber().compareTo(roomTwo.getRoomNumber()))
				.collect(Collectors.toList());
		if (rooms.isEmpty()) {
			throw new CategoryNotFoundException("Ivalid category");
		}

		return rooms;
	}

	@Override
	public List<Room> getByCategory(String category) throws CategoryNotFoundException {
		List<Room> rooms = roomDao.findByCategory(category);
		rooms = rooms.stream()
				.sorted((roomThree, roomFour) -> roomThree.getRoomNumber().compareTo(roomFour.getRoomNumber()))
				.collect(Collectors.toList());
		if (rooms.isEmpty()) {
			throw new CategoryNotFoundException("Sorry searching category is not available");
		}
		return rooms;
	}

	@Override
	public List<Room> getByRoomType(String roomType, double price) throws TypeNotFoundException {
		List<Room> rooms = roomDao.findByRoomType(roomType, price);
		rooms = rooms.stream()
				.sorted((roomThree, roomFour) -> roomThree.getRoomNumber().compareTo(roomFour.getRoomNumber()))
				.collect(Collectors.toList());

		if (rooms.isEmpty()) {
			throw new TypeNotFoundException("Sorry your search is not available");
		}

		return rooms;
	}

	@Override
	public List<Room> getByLessPrice() {
		List<Room> rooms = roomDao.findByLessPrice();
		rooms = rooms.stream()
				.sorted((roomOne, roomTwo) -> ((Double) roomOne.getPrice()).compareTo(((Double) roomTwo.getPrice())))
				.collect(Collectors.toList());

		return rooms;
	}

	@Override
	public List<Room> getByAvailabilityAndType(LocalDate startDate, String category, String roomType)
			throws RoomNotAvailableException {

		List<Room> rooms = new ArrayList<>();
		rooms = roomDao.findByAvailabilityAndType(startDate, category, roomType).stream()
				.sorted((roomOne, roomTwo) -> roomOne.getArrivalDate().compareTo(roomTwo.getArrivalDate()))
				.collect(Collectors.toList());

		if (rooms.isEmpty()) {
			throw new RoomNotAvailableException("Sorry rooms are not available for your search");
		} else {

			return rooms;
		}
	}

	@Override
	public List<Room> getByAvailability(LocalDate startDate) throws RoomNotAvailableException {
		List<Room> rooms = new ArrayList<>();
		rooms = roomDao.findByAvailability(startDate).stream()
				.sorted((roomOne, roomTwo) -> roomOne.getArrivalDate().compareTo(roomTwo.getArrivalDate()))
				.collect(Collectors.toList());

		if (rooms.isEmpty()) {
			throw new RoomNotAvailableException("Sorry rooms are not available for your search");
		} else {

			return rooms;
		}
	}
}
