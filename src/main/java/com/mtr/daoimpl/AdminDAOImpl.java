package com.mtr.daoimpl;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mtr.dao.AdminDAO;
import com.mtr.mapper.AdminMapper;
import com.mtr.mapper.MovieMapper;
import com.mtr.mapper.MoviesListInTheatreMapper;
import com.mtr.mapper.TheatreMapper;
import com.mtr.pojo.Admin;
import com.mtr.pojo.GetMoviesInTheatre;
import com.mtr.pojo.Movie;
import com.mtr.pojo.MoviesListInTheatre;
import com.mtr.pojo.Theatre;
import com.mtr.pojo.TheatreMovie;


public class AdminDAOImpl implements AdminDAO{
	
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(AdminDAOImpl.class);
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	private Admin findAdmin(String id) {
		Admin checker;
		try {	
			String sql = "SELECT * FROM ADMIN WHERE ADMIN_ID=?";
			checker = jdbcTemplate.queryForObject(sql, new Object[] {id}, new AdminMapper());
		}
		catch (Exception e) {
			logger.error("User Name does not exsist!");
			checker=null;
		}
		
		if(checker == null)
		{
			return null;
		}
		logger.info("Admin's User Name found in DB");
		return checker;
	}
	
	public boolean login(String id, String password) {
		Admin admin = this.findAdmin(id);
		
		if(admin==null)
		{
			return false;
		}
		else if(id.equals(admin.getId()) && password.equals(admin.getPassword()))
		{
			logger.info("Admin Logged In");
			return true;
		}
		logger.error("User Name Password Doesn't match");
		return false;
	}

	public boolean addTheatre(Theatre theatre) {
		int checker;
		try {
			String sql = "INSERT INTO THEATRE(THEATRE_NAME, LATITUDE, LONGITUDE,NO_OF_SCREENS) VALUES(?,?,?,?)";
			checker = jdbcTemplate.update(sql,theatre.getName(),theatre.getLatitude(),theatre.getLongitude(),theatre.getNumberOfScreen());
		}
		catch (Exception e) {
			logger.error("Values not inserted."
					+ "Reasons may be:"
					+ "1.Trying to insert NULL values"
					+ "2.Trying to add a Theatre which is already in DB");
			checker=0;
		}
		if(checker == 0)
		{
			return false;
		}
		logger.info("Theatre Added successfully!");
		return true;
	}

	private Movie findMovieId(String name, int duration)
	{
		String sql = "SELECT * FROM MOVIE WHERE MOVIE_NAME=? AND MOVIE_DURATION=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {name, duration}, new MovieMapper());

	}
	
	public void addMovie(Movie movie) {
		
		int checker;
		try {
			String sql = "INSERT INTO MOVIE(MOVIE_NAME, MOVIE_DURATION) VALUES(?,?)";
			jdbcTemplate.update(sql,movie.getName(),movie.getDuration());
			Movie getId= findMovieId(movie.getName(), movie.getDuration());
		
			for(int i=0;i<movie.getGenre().size();i++)
			{
				String sql2 = "INSERT INTO GENRE_MOVIE(MOVIE_ID, GENRE_ID) VALUES(?,?)";
				jdbcTemplate.update(sql2,getId.getId(),movie.getGenre().get(i));
			}
		}
		catch(Exception e)
		{
			
		}
	}

	public List<Theatre> getAllTheatres() {
		String sql="SELECT * FROM THEATRE";
		return jdbcTemplate.query(sql, new TheatreMapper());
	}

	public List<Movie> getAllMovies() {
		String sql="SELECT * FROM MOVIE";
		return jdbcTemplate.query(sql, new MovieMapper());
	}

	private Movie getDuration(int id)
	{
		String sql = "SELECT * FROM MOVIE WHERE MOVIE_ID=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new MovieMapper());
	}
	
	public void addMovieInTheatre(TheatreMovie theatreMovie) {
		
		Time endTime=theatreMovie.getStartTime();
		Movie temp= getDuration(theatreMovie.getMovieId());
		long duration = temp.getDuration();
		LocalTime localtime = endTime.toLocalTime();
		localtime = localtime.plusMinutes(duration);
		Time finalTime = java.sql.Time.valueOf(localtime);
		
		String sql = "INSERT INTO THEATRE_MOVIE(THEATRE_ID, MOVIE_ID, SCREEN,DATE_FROM, DATE_TO,TIME_FROM,TIME_TO) VALUES(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,theatreMovie.getTheatreId(),theatreMovie.getMovieId(),theatreMovie.getScreen(),theatreMovie.getStartDate(),theatreMovie.getEndDate(),theatreMovie.getStartTime(),finalTime);

	}

	@Override
	public List<MoviesListInTheatre> listMoviesInTheatre(GetMoviesInTheatre getMoviesInTheatre) {

		String sql = "SELECT * FROM MOVIE JOIN THEATRE_MOVIE ON MOVIE.MOVIE_ID = THEATRE_MOVIE.MOVIE_ID "
				+ "WHERE THEATRE_MOVIE.THEATRE_ID =? and date_from<=? and date_to>=? "
				+ "ORDER BY MOVIE_NAME ASC";
		return jdbcTemplate.query(sql, new Object[] {getMoviesInTheatre.getTheatreId(),getMoviesInTheatre.getShowDate(),getMoviesInTheatre.getShowDate()}, new MoviesListInTheatreMapper());
	}

	@Override
	public void editMovieInTheatre(TheatreMovie theatreMovie) {
		Time endTime=theatreMovie.getStartTime();
		Movie temp= getDuration(theatreMovie.getMovieId());
		long duration = temp.getDuration();
		LocalTime localtime = endTime.toLocalTime();
		localtime = localtime.plusMinutes(duration);
		Time finalTime = java.sql.Time.valueOf(localtime);

		String sql = "UPDATE THEATRE_MOVIE SET THEATRE_ID=?, MOVIE_ID=?, SCREEN=?,DATE_FROM=?, DATE_TO=?,TIME_FROM=?,TIME_TO=? WHERE THEATRE_MOVIE_ID=?";
		jdbcTemplate.update(sql,theatreMovie.getTheatreId(),theatreMovie.getMovieId(),theatreMovie.getScreen(),theatreMovie.getStartDate(),theatreMovie.getEndDate(),theatreMovie.getStartTime(),finalTime,theatreMovie.getTheatreMovieId());	
		}

}
