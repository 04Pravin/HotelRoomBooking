package com.roombooking.model;

public enum RoomType {
	STANDARDQUEEN("Queen SIze"), 
	STANDARDKING("King Size"), 
	STANDARD("Standard"),
	DELUXE("Double"), 
	SUPERDELUXE("Family"),
	CLASSIC("Single"),
	SUITES("Suite");
	
	public String type;

	private RoomType(String type) {
		this.type = type;
	}
	
	
}
