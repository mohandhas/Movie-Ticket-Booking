package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mtr.pojo.Movie;

public class MovieMapper implements org.springframework.jdbc.core.RowMapper<Movie> {

	public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Movie movie= new Movie();
		movie.setId(rs.getInt("MOVIE_ID"));
		movie.setName(rs.getString("MOVIE_NAME"));
		movie.setRatingCount(rs.getInt("RATING_HEAD"));
		movie.setRating(rs.getDouble("RATING_COUNT"));
		movie.setDuration(rs.getInt("MOVIE_DURATION"));
		return movie;
	}

}
