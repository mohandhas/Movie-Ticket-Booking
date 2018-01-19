package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mtr.pojo.MoviesListInTheatreSQL;

public class MoviesListInTheatreSQLMapper implements RowMapper<MoviesListInTheatreSQL>{
	
	public MoviesListInTheatreSQL mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MoviesListInTheatreSQL movie= new MoviesListInTheatreSQL();
		movie.setMovieId(rs.getInt("MOVIE_ID"));
		movie.setMovieName(rs.getString("MOVIE_NAME"));
		movie.setRating(rs.getDouble("RATING_COUNT"));
		movie.setRatingCount(rs.getInt("RATING_HEAD"));
		movie.setDuration(rs.getInt("MOVIE_DURATION"));
		movie.setTheatreMovieId(rs.getString("THEATRE_MOVIE_ID"));
		movie.setScreen(rs.getInt("SCREEN"));
		movie.setShowTime(rs.getTime("TIME_FROM"));
		movie.setGenre(rs.getInt("GENRE_ID"));
		return movie;
	}

}
