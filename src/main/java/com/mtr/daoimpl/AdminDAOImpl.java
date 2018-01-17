package com.mtr.daoimpl;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mtr.dao.AdminDAO;
import com.mtr.mapper.AdminMapper;
import com.mtr.mapper.MovieMapper;
import com.mtr.mapper.TheatreMapper;
import com.mtr.pojo.Admin;
import com.mtr.pojo.Movie;
import com.mtr.pojo.Theatre;
import com.mtr.pojo.TheatreMovie;


public class AdminDAOImpl implements AdminDAO{
	
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	private Admin findAdmin(String id) {
		String sql = "SELECT * FROM ADMIN WHERE ADMIN_ID=?";
		Admin checker = jdbcTemplate.queryForObject(sql, new Object[] {id}, new AdminMapper());
		
		if(checker == null)
		{
			return null;
		}
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
			return true;
		}
		return false;
	}

	public void addTheatre(Theatre theatre) {

		String sql = "INSERT INTO THEATRE(THEATRE_NAME, LATITUDE, LONGITUDE,NO_OF_SCREENS) VALUES(?,?,?,?)";
		jdbcTemplate.update(sql,theatre.getName(),theatre.getLatitudes(),theatre.getLongitudes(),theatre.getNumberOfScreens());
	}

	public void addMovie(Movie movie) {
	
		String sql = "INSERT INTO MOVIE(MOVIE_NAME, MOVIE_DURATION, GENRE1,GENRE2,GENRE3) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql,movie.getMovie(),movie.getDuration(),movie.getGenere1(),movie.getGenere2(),movie.getGenere3());
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
		int duration = temp.getDuration();
		 LocalTime localtime = endTime.toLocalTime();
		 endTime = new java.sql.Time(localtime.plusMinutes(duration));
		String sql = "INSERT INTO THEATRE_MOVIE(THEATRE_ID, MOVIE_ID, SCREEN,DATE_FROM, DATE_TO,TIME_FROM,TIME_TO) VALUES(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,theatreMovie.getTheatreId(),theatreMovie.getMovieId(),theatreMovie.getScreen(),theatreMovie.getStartDate(),theatreMovie.getEndDate(),theatreMovie.getStartTime(),theatreMovie.getEndTime());

	}

}
