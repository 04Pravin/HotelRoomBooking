package com.roombooking.service;

import com.roombooking.user.User;

public interface IUserService {
	
	String register(User user);
	
	boolean login(String username, String password);
	
	int changePassword(String password, String username);
		
}
