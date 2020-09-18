package com.practice.model;

public class Tournament {
	private final int id;
	private final String tourName;

	public Tournament(int id, String tourName) {
		super();
		this.id = id;
		this.tourName = tourName;
	}

	public int getId() {
		return id;
	}

	public String getTourName() {
		return tourName;
	}
	
	
}
