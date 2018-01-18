package com.mtr.pojo;

import java.sql.Date;
import java.sql.Time;

public class Ticket {

	private int theatreMovieId;
	private String email;
	private String seatNumber;
	private Date date;
	private Time showTime;
	
	
	public Ticket() {
		super();
	}

	public Ticket(int theatreMovieId, String email, String seatNumber, Date date, Time showTime) {
		super();
		this.theatreMovieId = theatreMovieId;
		this.email = email;
		this.seatNumber = seatNumber;
		this.date = date;
		this.showTime = showTime;
	}

	@Override
	public String toString() {
		return "Ticket [theatreMovieId=" + theatreMovieId + ", email=" + email + ", seatNumber=" + seatNumber
				+ ", date=" + date + ", showTime=" + showTime + "]";
	}

	public int getTheatreMovieId() {
		return theatreMovieId;
	}

	public void setTheatreMovieId(int theatreMovieId) {
		this.theatreMovieId = theatreMovieId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getShowTime() {
		return showTime;
	}

	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}
	
	
}
