package com.mtr.pojo;

import java.sql.Date;
import java.sql.Time;

public class BookedTicketsForParticularShow {

	private String theatreMovieId;
	private Date showDate;
	private Time showTime;
	public BookedTicketsForParticularShow(String theatreMovieId, Date showDate, Time showTime) {
		super();
		this.theatreMovieId = theatreMovieId;
		this.showDate = showDate;
		this.showTime = showTime;
	}
	@Override
	public String toString() {
		return "BookedTicketsForParticularShow [theatreMovieId=" + theatreMovieId + ", showDate=" + showDate
				+ ", showTime=" + showTime + "]";
	}
	public String getTheatreMovieId() {
		return theatreMovieId;
	}
	public void setTheatreMovieId(String theatreMovieId) {
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
	public BookedTicketsForParticularShow() {
		super();
	}
	
	
}
