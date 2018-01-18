package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mtr.pojo.MoviesListInTheatre;

public class MoviesListInTheatreMapper implements RowMapper<MoviesListInTheatre>{
	
	public MoviesListInTheatre mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MoviesListInTheatre movie= new MoviesListInTheatre();
		movie.setMovieId(rs.getInt("MOVIE_ID"));
		movie.setMovieName(rs.getString("MOVIE_NAME"));
		movie.setRating(rs.getDouble("RATING_COUNT"));
		movie.setRatingCount(rs.getInt("RATING_HEAD"));
		movie.setDuration(rs.getInt("MOVIE_DURATION"));
		movie.setTheatreMovieId(rs.getInt("THEATRE_MOVIE_ID"));
		movie.setScreen(rs.getInt("SCREEN"));
		movie.setShowTime(rs.getTime("TIME_FROM"));
		return movie;
	}

}
