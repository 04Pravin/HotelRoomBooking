package com.roombooking.service;

import com.roombooking.dao.IUserDao;
import com.roombooking.dao.UserDaoImpl;
import com.roombooking.user.User;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImpl();
	@Override
	public String register(User user) {
		return userDao.register(user);
	}

	@Override
	public boolean login(String username, String password) {
		
		return userDao.login(username, password);
	}

	@Override
	public int changePassword(String password, String username) {
			return userDao.changePassword(password, username);
	}


}
