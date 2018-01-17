package com.mtr.pojo;

public class Theatre {

	public int id;
	public String name;
	public double latitudes;
	public double longitudes;
	public int numberOfScreens;	

	public Theatre() {
		super();
	}
		
	public Theatre(int id, String name, double latitudes, double longitudes, int numberOfScreens) {
		super();
		this.id = id;
		this.name = name;
		this.latitudes = latitudes;
		this.longitudes = longitudes;
		this.numberOfScreens = numberOfScreens;
	}

	@Override
	public String toString() {
		return "Theatre [id=" + id + ", name=" + name + ", latitudes=" + latitudes + ", longitudes=" + longitudes
				+ ", numberOfScreens=" + numberOfScreens + "]";
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

	public double getLatitudes() {
		return latitudes;
	}

	public void setLatitudes(double latitudes) {
		this.latitudes = latitudes;
	}

	public double getLongitudes() {
		return longitudes;
	}

	public void setLongitudes(double longitudes) {
		this.longitudes = longitudes;
	}

	public int getNumberOfScreens() {
		return numberOfScreens;
	}

	public void setNumberOfScreens(int numberOfScreens) {
		this.numberOfScreens = numberOfScreens;
	}	
	
}
