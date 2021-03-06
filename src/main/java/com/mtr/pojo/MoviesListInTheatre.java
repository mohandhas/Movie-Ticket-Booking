package com.mtr.pojo;

import java.sql.Time;
import java.util.LinkedHashSet;
import java.util.Set;

public class MoviesListInTheatre {
	private int movieId;
	private String movieName;
	private double rating;
	private int ratingCount;
	private int duration;
	private String theatreMovieId;
	private int screen;
	private Set<Time> showTime= new LinkedHashSet<>();
	private Set<Integer> genre=new LinkedHashSet<>();
	
	
	public MoviesListInTheatre() {
		super();
	}


	public MoviesListInTheatre(int movieId, String movieName, double rating, int ratingCount, int duration,
			String theatreMovieId, int screen, Set<Time> showTime, Set<Integer> genre) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.rating = rating;
		this.ratingCount = ratingCount;
		this.duration = duration;
		this.theatreMovieId = theatreMovieId;
		this.screen = screen;
		this.showTime = showTime;
		this.genre = genre;
	}


	public int getMovieId() {
		return movieId;
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public int getRatingCount() {
		return ratingCount;
	}


	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getTheatreMovieId() {
		return theatreMovieId;
	}


	public void setTheatreMovieId(String theatreMovieId) {
		this.theatreMovieId = theatreMovieId;
	}


	public int getScreen() {
		return screen;
	}


	public void setScreen(int screen) {
		this.screen = screen;
	}


	public Set<Time> getShowTime() {
		return showTime;
	}


	public void setShowTime(Set<Time> showTime) {
		this.showTime = showTime;
	}


	public Set<Integer> getGenre() {
		return genre;
	}


	public void setGenre(Set<Integer> genre) {
		this.genre = genre;
	}

	
	
}
