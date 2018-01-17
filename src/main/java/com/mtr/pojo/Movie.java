package com.mtr.pojo;


public class Movie {
	
	public int id;
	public String movie;
	public int duration;
	public String genere1;
	public String genere2;
	public String genere3;
	public int ratingCount;
	public double rating;
		
	public Movie() {
		super();
	}

	
	
	public Movie(int id, String movie, int duration, String genere1, String genere2, String genere3, int ratingCount,
			double rating) {
		super();
		this.id = id;
		this.movie = movie;
		this.duration = duration;
		this.genere1 = genere1;
		this.genere2 = genere2;
		this.genere3 = genere3;
		this.ratingCount = ratingCount;
		this.rating = rating;
	}



	@Override
	public String toString() {
		return "Movie [id=" + id + ", movie=" + movie + ", duration=" + duration + ", genere1=" + genere1 + ", genere2="
				+ genere2 + ", genere3=" + genere3 + ", ratingCount=" + ratingCount + ", rating=" + rating + "]";
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

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getGenere1() {
		return genere1;
	}

	public void setGenere1(String genere1) {
		this.genere1 = genere1;
	}

	public String getGenere2() {
		return genere2;
	}

	public void setGenere2(String genere2) {
		this.genere2 = genere2;
	}

	public String getGenere3() {
		return genere3;
	}

	public void setGenere3(String genere3) {
		this.genere3 = genere3;
	}
	
	
}
