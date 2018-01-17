package com.mtr.pojo;

import java.sql.Date;

public class Ticket {

	public int theatreMovieId;
	public int userId;
	public String seatNumber;
	public Date date;
	public int guest;
	
	
	public Ticket() {
		super();
	}
	
	
	public Ticket(int theatreMovieId, int userId, String seatNumber, Date date, int guest) {
		super();
		this.theatreMovieId = theatreMovieId;
		this.userId = userId;
		this.seatNumber = seatNumber;
		this.date = date;
		this.guest = guest;
	}


	@Override
	public String toString() {
		return "Ticket [theatreMovieId=" + theatreMovieId + ", userId=" + userId + ", seatNumber=" + seatNumber
				+ ", date=" + date + ", guest=" + guest + "]";
	}
	
	
	public String getSeatNumber() {
		return seatNumber;
	}


	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}


	public int getTheatreMovieId() {
		return theatreMovieId;
	}
	public void setTheatreMovieId(int theatreMovieId) {
		this.theatreMovieId = theatreMovieId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getGuest() {
		return guest;
	}
	public void setGuest(int guest) {
		this.guest = guest;
	}
}
