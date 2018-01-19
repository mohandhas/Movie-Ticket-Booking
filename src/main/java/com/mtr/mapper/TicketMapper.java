package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mtr.pojo.Ticket;

public class TicketMapper implements org.springframework.jdbc.core.RowMapper<Ticket> {

	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {

		Ticket ticket= new Ticket();
		ticket.setTheatreMovieId(rs.getString("THEATRE_MOVIE_ID"));
		ticket.setEmail(rs.getString("EMAIL"));
		ticket.setSeatNumber(rs.getString("SEAT_NO"));
		ticket.setDate(rs.getDate("DATE"));
		ticket.setShowTime(rs.getTime("SHOW_TIME"));
		return ticket;
	}
}

