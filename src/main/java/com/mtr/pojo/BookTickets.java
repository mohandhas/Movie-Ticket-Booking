package com.mtr.pojo;

import java.sql.Date;
import java.sql.Time;

public class BookTickets {

	private int theatreMovieId;
	private Date showDate;
	private Time showTime;
	
	public BookTickets(int theatreMovieId, Date showDate, Time showTime) {
		super();
		this.theatreMovieId = theatreMovieId;
		this.showDate = showDate;
		this.showTime = showTime;
	}
	
	public BookTickets() {
		super();
	}
	
	@Override
	public String toString() {
		return "AvailableTickets [theatreMovieId=" + theatreMovieId + ", showDate=" + showDate + ", showTime="
				+ showTime + "]";
	}
	
	public int getTheatreMovieId() {
		return theatreMovieId;
	}
	
	public void setTheatreMovieId(int theatreMovieId) {
		this.theatreMovieId = theatreMovieId;
	}
	
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public Time getShowTime() {
		return showTime;
	}
	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}
}
