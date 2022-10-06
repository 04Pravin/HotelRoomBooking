package com.roombooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.roombooking.model.Room;
import com.roombooking.util.DbConnection;
import com.roombooking.util.Queries;

public class BookingDaoImpl implements IBookingDao {

	DbConnection dbConnection = new DbConnection();

	@Override
	public Room booking(String category, String type) {
		Room room = new Room();
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement =null;
		try {
			statement  = connection.prepareStatement(Queries.BOOKINGQUERY);
			statement.setString(1, category);
			statement.setString(2, type);
			ResultSet resultSet = statement.executeQuery();
			if(!resultSet.next()) {
				return null;
			} else {
				
				int roomNumber = resultSet.getInt(1);
				String roomCategory = resultSet.getString(2);
				String roomType = resultSet.getString(3);
				LocalDate startDate = resultSet.getDate(4).toLocalDate();
				LocalDate endDate = resultSet.getDate(5).toLocalDate();
				int noOfGuests = resultSet.getInt(6);
				double roomPrice = resultSet.getDouble(7);
				int availability = resultSet.getInt(8);
				room.setRoomNumber(roomNumber);
				room.setCategory(roomCategory);
				room.setType(roomType);
				room.setArrivalDate(startDate);
				room.setDepartDate(endDate);
				room.setNoOfGuests(noOfGuests);
				room.setPrice(roomPrice);
				room.setAvailability(availability);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
			if(statement!=null) {
					statement.close();
			}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			DbConnection.closeConnection();
			}
		return room;
}

	/**
	 * @param roomNumber
	 */
	@Override
	public void updateAvailablity(Integer roomNumber) {
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement =null;
		try {
			statement  = connection.prepareStatement(Queries.BOOKINGUPDATEQUERY);
			statement.setInt(1, roomNumber);
			statement.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			if(statement!=null) {
					statement.close();
			}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			DbConnection.closeConnection();
			}
	}

}
