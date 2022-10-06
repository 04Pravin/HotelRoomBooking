package com.roombooking.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.roombooking.model.Room;

public interface IRoomMapper {

		public List<Room> rowMap(ResultSet resultSet) throws SQLException;
}
