package com.roombooking.dao;

import com.roombooking.user.User;

public interface IUserDao {
	
	String register(User user);
	
	boolean login(String username, String password);
	
	int changePassword(String password, String username);
}
