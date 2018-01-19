package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mtr.pojo.MovieInTheatre;

public class TheatreMovieMapper implements org.springframework.jdbc.core.RowMapper<MovieInTheatre> {

	public MovieInTheatre mapRow(ResultSet rs, int rowNum) throws SQLException {

		MovieInTheatre tm= new MovieInTheatre();
		tm.setTheatreMovieId(rs.getString("THEATRE_MOVIE_ID"));
		tm.setTheatreId(rs.getInt("THEATRE_ID"));
		tm.setMovieId(rs.getInt("MOVIE_ID"));
		tm.setScreen(rs.getInt("SCREEN"));
		tm.setStartDate(rs.getDate("DATE_FROM"));
		tm.setEndDate(rs.getDate("DATE_TO"));
		tm.setStartTime(rs.getTime("TIME_FROM"));
		tm.setEndTime(rs.getTime("TIME_TO"));
		return tm;
	}

}
