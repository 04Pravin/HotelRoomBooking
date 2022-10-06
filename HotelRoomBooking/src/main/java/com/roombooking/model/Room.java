package com.roombooking.model;

import java.time.LocalDate;

public class Room {
	private Integer roomNumber;
	private String category;
	private String type;
	private LocalDate arrivalDate;
	private LocalDate departDate;
	private int noOfGuests;
	private double price;
	private int availability;

	public Room(Integer roomNumber, String category, String type, LocalDate arrivalDate, LocalDate departDate,
			int noOfGuests, double price, int availability) {
		super();
		this.roomNumber = roomNumber;
		this.category = category;
		this.type = type;
		this.arrivalDate = arrivalDate;
		this.departDate = departDate;
		this.noOfGuests = noOfGuests;
		this.price = price;
		this.availability = availability;
	}

	public Room(String category, String type, LocalDate arrivalDate, LocalDate departDate, int noOfGuests,
			double price, int availability) {
		super();
		this.category = category;
		this.type = type;
		this.arrivalDate = arrivalDate;
		this.departDate = departDate;
		this.noOfGuests = noOfGuests;
		this.price = price;
		this.availability = availability;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
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

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalDate getDepartDate() {
		return departDate;
	}

	public void setDepartDate(LocalDate departDate) {
		this.departDate = departDate;
	}

	public int getNoOfGuests() {
		return noOfGuests;
	}

	public void setNoOfGuests(int noOfGuests) {
		this.noOfGuests = noOfGuests;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", category=" + category + ", type=" + type + ", arrivalDate="
				+ arrivalDate + ", departDate=" + departDate + ", noOfGuests=" + noOfGuests + ", price=" + price
				+ ", availability=" + availability + "]";
	}


}
