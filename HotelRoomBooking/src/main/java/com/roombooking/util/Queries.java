package com.roombooking.util;

public class Queries {

	public static final String INSERTQUERY = "insert into Room (category,roomType,startDate,endDate,guestsAllowed, price, availability) values (?,?,?,?,?,?,?)";
	
	public static final String UPDATEQUERY = "update Room set price = ? where roomNumber = ?";
	
	public static final String DELETEQUERY = "delete from Room where roomNumber = ?";
	
	public static final String RETRIVEQUERY = "select * from Room where availability = 1";
	
	public static final String QUERYBYCATEGORYANDPRICEFEES = "select * from Room where category = ? and price = ?";
	
	public static final String QUERYBYCATEGORY = "select * from Room where category = ?";
	
	public static final String QUERYBYTYPEANDPRICE = "select * from Room where roomType = ? and price = ?";
	
	public static final String QUERYBYPRICE = "select * from Room ";
	
	public static final String QUERYBYAVAILABILITYANDTYPE = "select * from Room where startdate >= ? and category = ? and roomType = ?";
	
	public static final String QUERYBYAVAILABILITY = "select * from Room where startdate >= ?";
	
	public static final String QUERYBYREGISTER =  "insert into user (username, name, password, location, mobilenumber) values (?,?,?,?,?)";
	
	public static final String LOGINQUERY = "select * from user where username = ? and password = ?";
	
	public static final String QUERYBYCHANGEPASSWORD = "update user set password = ? where username = ? ";
	
	public static final String BOOKINGQUERY = "select * from Room where category = ? and roomType = ?";
	
	public static final String BOOKINGUPDATEQUERY = "update room set availability = 0 where roomNumber = ?";
}
