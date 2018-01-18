package com.mtr.pojo;

public class Theatre {

	private int id;
	private String name;
	private double latitude;
	private double longitude;
	private int numberOfScreen;	

	public Theatre() {
		super();
	}

	@Override
	public String toString() {
		return "Theatre [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", numberOfScreens=" + numberOfScreen + "]";
	}

	public Theatre(int id, String name, double latitude, double longitude, int numberOfScreen) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.numberOfScreen = numberOfScreen;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public double getLatitude() {
		return latitude;
	}



	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	public double getLongitude() {
		return longitude;
	}



	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}



	public int getNumberOfScreen() {
		return numberOfScreen;
	}



	public void setNumberOfScreen(int numberOfScreen) {
		this.numberOfScreen = numberOfScreen;
	}
	
	
}
