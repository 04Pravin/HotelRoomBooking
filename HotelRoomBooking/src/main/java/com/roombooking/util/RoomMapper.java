package com.roombooking.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.roombooking.model.Room;

public class RoomMapper implements IRoomMapper {

	@Override
	public List<Room> rowMap(ResultSet resultSet) throws SQLException {
		List<Room> rooms = new ArrayList<>();
		while(resultSet.next()) {
				Room room = new Room();
				int roomNumber = resultSet.getInt(1);
				String category = resultSet.getString(2);
				String roomType = resultSet.getString(3);
				LocalDate startDate = resultSet.getDate(4).toLocalDate();
				LocalDate endDate = resultSet.getDate(5).toLocalDate();
				int noOfGuests = resultSet.getInt(6);
				double price = resultSet.getDouble(7);
				int availability = resultSet.getInt(8);
				room.setRoomNumber(roomNumber);
				room.setCategory(category);
				room.setType(roomType);
				room.setArrivalDate(startDate);
				room.setDepartDate(endDate);
				room.setNoOfGuests(noOfGuests);
				room.setPrice(price);
				room.setAvailability(availability);

				rooms.add(room);

			
		}
		return rooms;
	}

}
