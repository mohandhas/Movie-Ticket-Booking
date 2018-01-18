package com.mtr.pojo;

import java.sql.Time;

public class MoviesListInTheatre {
	private int movieId;
	private String movieName;
	private double rating;
	private int ratingCount;
	private int duration;
	private int theatreMovieId;
	private int screen;
	private Time showTime;
	
	public MoviesListInTheatre() {
		super();
	}

	public MoviesListInTheatre(int movieId, String movieName, double rating, int ratingCount, int duration,
			int theatreMovieId, int screen, Time showTime) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.rating = rating;
		this.ratingCount = ratingCount;
		this.duration = duration;
		this.theatreMovieId = theatreMovieId;
		this.screen = screen;
		this.showTime = showTime;
	}

	@Override
	public String toString() {
		return "MoviesInTheatreList [movieId=" + movieId + ", movieName=" + movieName + ", rating=" + rating
				+ ", ratingCount=" + ratingCount + ", duration=" + duration + ", theatreMovieId=" + theatreMovieId
				+ ", screen=" + screen + ", showTime=" + showTime + "]";
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

	public int getTheatreMovieId() {
		return theatreMovieId;
	}

	public void setTheatreMovieId(int theatreMovieId) {
		this.theatreMovieId = theatreMovieId;
	}

	public int getScreen() {
		return screen;
	}

	public void setScreen(int screen) {
		this.screen = screen;
	}

	public Time getShowTime() {
		return showTime;
	}

	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}
	
	
}
