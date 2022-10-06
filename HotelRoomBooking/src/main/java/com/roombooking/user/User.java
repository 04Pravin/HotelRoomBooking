package com.roombooking.user;

public class User {
	private String username;
	private String name;
	private String password;
	private Integer userId;
	private String location;
	private long mobileNumber;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(String username, String name, String password, Integer userId, String location, long mobileNumber) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.userId = userId;
		this.location = location;
		this.mobileNumber = mobileNumber;
	}



	public User(String username, String name, String password, String location, long mobileNumber) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.location = location;
		this.mobileNumber = mobileNumber;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", password=" + password + ", userId=" + userId
				+ ", location=" + location + ", mobileNumber=" + mobileNumber + "]";
	}
	
	

}
