package com.roombooking.booking;


public class Booking {
	private String category;
	private String type;
	
	
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Booking(String category, String type) {
		super();
		this.category = category;
		this.type = type;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return "Booking [category=" + category + ", type=" + type + "]";
	}
	
	
	
	
}
