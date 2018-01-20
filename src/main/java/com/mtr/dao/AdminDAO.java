package com.mtr.dao;

import java.util.List;

import javax.sql.DataSource;

import com.mtr.pojo.Addons;
import com.mtr.pojo.BookedTicketsForParticularShow;
import com.mtr.pojo.GetMovieInTheatre;
import com.mtr.pojo.Movie;
import com.mtr.pojo.MoviesListInTheatre;
import com.mtr.pojo.Theatre;
import com.mtr.pojo.MovieInTheatre;

public interface AdminDAO {
	
	void setDataSource(DataSource dataSource);
	
	public boolean login(String id, String password);
	
	public boolean addTheatre(Theatre theatre);
	
	public boolean addMovie(Movie movie);
	
	public boolean addAddon(Addons addon);
	
	public List<Theatre> getAllTheatres();
	
	public List<Movie> getAllMovies();
	
	public List<Addons> getAllAddons();
	
	public boolean addMovieInTheatre(MovieInTheatre theatreMovie);
	
	public List<MoviesListInTheatre> listMoviesInTheatre(GetMovieInTheatre getMoviesInTheatre);

	public boolean editMovieInTheatre(MovieInTheatre theatreMovie);

	public Integer getTicketCountInAParticularShow(BookedTicketsForParticularShow bookedTicketsForParticularShow);

	public boolean editAddonCost(Addons addon);


}
