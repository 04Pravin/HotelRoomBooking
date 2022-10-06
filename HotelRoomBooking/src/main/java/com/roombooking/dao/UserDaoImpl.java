package com.roombooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.roombooking.user.User;
import com.roombooking.util.DbConnection;
import com.roombooking.util.Queries;

public class UserDaoImpl implements IUserDao{

	DbConnection connection = new DbConnection();

	@Override
	public String register(User user) {
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		String result = null;
		try {
			statement = connection.prepareStatement(Queries.QUERYBYREGISTER);
			statement.setString(1,user.getUsername());
			statement.setString(2, user.getName());
			statement.setString(3,user.getPassword());
			statement.setString(4, user.getLocation());
			statement.setLong(5, user.getMobileNumber());
			int execute = statement.executeUpdate();
			if(execute == 1) {
				result = "successfully added";
			}else {
				result = "failed";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
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
	public boolean login(String username, String password) {
		//User user = new User();
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(Queries.LOGINQUERY);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				
				return true;
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
		return false;
	}

	@Override
	public int changePassword(String password, String username) {
		Connection connection = DbConnection.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(Queries.QUERYBYCHANGEPASSWORD);
			statement.setString(1, password);
			statement.setString(2, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(statement!=null) {
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
		}
		return 1;
	}
	
	
}
