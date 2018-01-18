package com.mtr.pojo;

public class Ratings {

	private int rating;
	private int movieId;
	@Override
	public String toString() {
		return "Ratings [rating=" + rating + ", movieId=" + movieId + "]";
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public Ratings(int rating, int movieId) {
		super();
		this.rating = rating;
		this.movieId = movieId;
	}
	public Ratings() {
		super();
	}
	
	
}
