package com.roombooking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.roombooking.model.Room;
import com.roombooking.util.DbConnection;
import com.roombooking.util.Queries;
import com.roombooking.util.RoomMapper;

public class RoomDaoImpl implements IRoomDao {
	DbConnection dbconnection = new DbConnection();
	RoomMapper roomMap = new RoomMapper();

	@Override
	public int addRoom(Room room) {
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		int result = 0;
		try {
			statement = connection.prepareStatement(Queries.INSERTQUERY);
			statement.setString(1, room.getCategory());
			statement.setString(2, room.getType());
			statement.setDate(3, Date.valueOf(room.getArrivalDate()));
			statement.setDate(4, Date.valueOf(room.getDepartDate()));
			statement.setInt(5, room.getNoOfGuests());
			statement.setDouble(6, room.getPrice());
			statement.setInt(7, room.getAvailability());
			int query = statement.executeUpdate();
			if(query==1) {
				result =  1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			DbConnection.closeConnection();
		}
		return result;
	}

	@Override
	public int updateRoom(int roomNumber, double fare) {
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		int result = 0;
		try {
			statement = connection.prepareStatement(Queries.UPDATEQUERY);
			statement.setDouble(1, fare);
			statement.setInt(2, roomNumber);
			int query = statement.executeUpdate();
			if(query == 1) {
				result = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			DbConnection.closeConnection();
		}
		return result;

	}

	@Override
	public int deleteRoom(int roomNumber) {
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		int result = 0;
		try {
			statement = connection.prepareStatement(Queries.DELETEQUERY);
			statement.setInt(1, roomNumber);
			int query = statement.executeUpdate();
			if(query==1) {
				result = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return result;
	}

	@Override
	public List<Room> findAllRooms() {
		List<Room> rooms = new ArrayList<>();
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(Queries.RETRIVEQUERY);
			ResultSet resultSet = statement.executeQuery();
			rooms = roomMap.rowMap(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return rooms;
	}

	@Override
	public List<Room> findByCategoryAndPrice(String category, double price) {
		List<Room> rooms = new ArrayList<>();
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(Queries.QUERYBYCATEGORYANDPRICEFEES);
			statement.setString(1, category);
			statement.setDouble(2, price);
			ResultSet resultSet = statement.executeQuery();
			rooms = roomMap.rowMap(resultSet);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return rooms;
	}

	@Override
	public List<Room> findByCategory(String category) {
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		List<Room> categoryList = new ArrayList<>();
		try {
			statement = connection.prepareStatement(Queries.QUERYBYCATEGORY);
			statement.setString(1, category);
			ResultSet resultSet = statement.executeQuery();
			categoryList = roomMap.rowMap(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return categoryList;
	}

	@Override
	public List<Room> findByRoomType(String roomType, double price) {
		List<Room> rooms = new ArrayList<>();
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(Queries.QUERYBYTYPEANDPRICE);
			statement.setString(1, roomType);
			statement.setDouble(2, price);
			ResultSet resultSet = statement.executeQuery();
			rooms = roomMap.rowMap(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			DbConnection.closeConnection();
		}
		return rooms;
	}

	@Override
	public List<Room> findByLessPrice() {
		List<Room> priceList = new ArrayList<>();
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(Queries.QUERYBYPRICE);
			ResultSet resultSet = statement.executeQuery();
			priceList = roomMap.rowMap(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}

		return priceList;
	}

	@Override
	public List<Room> findByAvailabilityAndType(LocalDate startDate, String category, String roomType) {
		List<Room> rooms = new ArrayList<>();
		Date date = Date.valueOf(startDate);
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(Queries.QUERYBYAVAILABILITYANDTYPE);
			statement.setDate(1, date);
			statement.setString(2, category);
			statement.setString(3, roomType);
			ResultSet resultSet = statement.executeQuery();
			rooms = roomMap.rowMap(resultSet);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return rooms;
	}

	@Override
	public List<Room> findByAvailability(LocalDate startDate) {
		List<Room> rooms = new ArrayList<>();
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		Date date = Date.valueOf(startDate);
		try {
			statement = connection.prepareStatement(Queries.QUERYBYAVAILABILITY);
			statement.setDate(1, date);
			ResultSet resultSet = statement.executeQuery();
			rooms = roomMap.rowMap(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbConnection.closeConnection();
		}
		return rooms;
	}

}
