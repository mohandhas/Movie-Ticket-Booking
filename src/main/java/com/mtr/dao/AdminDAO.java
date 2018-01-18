package com.mtr.dao;

import java.util.List;

import javax.sql.DataSource;

import com.mtr.pojo.GetMoviesInTheatre;
import com.mtr.pojo.Movie;
import com.mtr.pojo.MoviesListInTheatre;
import com.mtr.pojo.Theatre;
import com.mtr.pojo.TheatreMovie;

public interface AdminDAO {
	
	void setDataSource(DataSource dataSource);
	
	public boolean login(String id, String password);
	
	public void addTheatre(Theatre theatre);
	
	public void addMovie(Movie movie);
	
	public List<Theatre> getAllTheatres();
	
	public List<Movie> getAllMovies();
	
	public void addMovieInTheatre(TheatreMovie theatreMovie);
	
	public List<MoviesListInTheatre> listMoviesInTheatre(GetMoviesInTheatre getMoviesInTheatre);

	public void editMovieInTheatre(TheatreMovie theatreMovie);

}
