package com.mtr.pojo;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	
	private int id;
	private String name;
	private int duration;
	private List<Integer> genre= new ArrayList<Integer>();
	private int ratingCount;
	private double rating;
		
	public Movie() {
		super();
	}

	
	
	public Movie(int id, String name, int duration, List<Integer> genre, int ratingCount, double rating) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.genre = genre;
		this.ratingCount = ratingCount;
		this.rating = rating;
	}



	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", duration=" + duration + ", genre=" + genre + ", ratingCount="
				+ ratingCount + ", rating=" + rating + "]";
	}



	public List<Integer> getGenre() {
		return genre;
	}

	public void setGenre(List<Integer> genre) {
		this.genre = genre;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
}
