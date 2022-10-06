package com.roombooking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {
	static Connection connection;
	static PreparedStatement statement;

	public static Connection openConnection() {
		String url = "jdbc:mysql://localhost:3306/roombooking";
		String username = "root";
		String password = "root";
		statement = null;
		try {
			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
