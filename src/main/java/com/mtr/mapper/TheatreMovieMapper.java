package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mtr.pojo.TheatreMovie;

public class TheatreMovieMapper implements org.springframework.jdbc.core.RowMapper<TheatreMovie> {

	public TheatreMovie mapRow(ResultSet rs, int rowNum) throws SQLException {

		TheatreMovie tm= new TheatreMovie();
		tm.setTheatreMovieId(rs.getInt("THEATRE_MOVIE_ID"));
		tm.setTheatreId(rs.getInt("THEATRE_ID"));
		tm.setMovieId(rs.getInt("MOVIE_ID"));
		tm.setScreen(rs.getInt("SCREEN"));
		tm.setStartDate(rs.getDate("DATE_FROM"));
		tm.setEndDate(rs.getDate("DATE_FROM"));
		tm.setStartTime(rs.getTime("DATE_FROM"));
		tm.setEndTime(rs.getTime("DATE_FROM"));
		return tm;
	}

}
