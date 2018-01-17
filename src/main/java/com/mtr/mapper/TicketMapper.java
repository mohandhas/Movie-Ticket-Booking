package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mtr.pojo.Ticket;

public class TicketMapper implements org.springframework.jdbc.core.RowMapper<Ticket> {

	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {

		Ticket ticket= new Ticket();
		ticket.setTheatreMovieId(rs.getInt("THEATRE_MOVIE_ID"));
		ticket.setUserId(rs.getInt("USER_ID"));
		ticket.setSeatNumber(rs.getString("SEAT_NUMBER"));
		ticket.setDate(rs.getDate("DATE"));
		ticket.setGuest(rs.getInt("GUEST"));
		return ticket;
	}
}
