package com.mtr.pojo;

import java.sql.Time;
import java.sql.Date;

public class TheatreMovie {

	public int theatreMovieId;
	public int theatreId;
	public int movieId;
	public int screen;
	public Date startDate;
	public Date endDate;
	public Time startTime;
	public Time endTime;
	
	
	public TheatreMovie() {
		super();
	}
	public TheatreMovie(int theatreMovieId, int theatreId, int movieId, int screen, Date startDate, Date endDate,
			Time startTime, Time endTime) {
		super();
		this.theatreMovieId = theatreMovieId;
		this.theatreId = theatreId;
		this.movieId = movieId;
		this.screen = screen;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "TheatreMovie [theatreMovieId=" + theatreMovieId + ", theatreId=" + theatreId + ", movieId=" + movieId
				+ ", screen=" + screen + ", startDate=" + startDate + ", endDate=" + endDate + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}
	public int getTheatreMovieId() {
		return theatreMovieId;
	}
	public void setTheatreMovieId(int theatreMovieId) {
		this.theatreMovieId = theatreMovieId;
	}
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getScreen() {
		return screen;
	}
	public void setScreen(int screen) {
		this.screen = screen;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	
}
