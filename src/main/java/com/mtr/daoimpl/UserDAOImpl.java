package com.mtr.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mtr.dao.UserDAO;
import com.mtr.mapper.MovieMapper;
import com.mtr.mapper.StringMapper;
import com.mtr.mapper.UserMapper;
import com.mtr.pojo.BookTickets;
import com.mtr.pojo.Movie;
import com.mtr.pojo.Ratings;
import com.mtr.pojo.Ticket;
import com.mtr.pojo.User;

public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}
	public boolean register(User user) {
		
		if(user.getName()!=null && user.getName()!=null && user.getEmail()!=null && user.getPassword()!=null && user.getPhone()!=null)
		{
			String sql = "INSERT INTO USER (USER_NAME,EMAIL,PASSWORD,PHONE,GENRE) VALUES(?,?,?,?,?)";
			int count = jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getPassword(),user.getPhone(),user.getFavoriteGenere());
			if(count!=0)
			{
				return false;
			}
		}
		return true;
	}

	public User login(String email, String password) {
		User user=this.findUser(email);
		if(user==null)
		{
			return null;
		}
		if(email.equals(user.getEmail()) && password.equals(user.getPassword()))
		{
			return user;
		}
	return null;
	}

	private User findUser(String email) {
		String sql = "SELECT * FROM USER WHERE EMAIL=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {email}, new UserMapper());
	}
	public List<Movie> getMoviesInTheatre(int id) {
		return null;
	}
	
	public void addTicket(Ticket ticket) {
		
	}
	
	private Movie getMovie(int id)
	{
		String sql = "SELECT * FROM MOVIE WHERE MOVIE_ID=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new MovieMapper());
	}
	
	public void updateRatings(Ratings rating) {
		Movie movie = getMovie(rating.getMovieId());
		int ratingHead = movie.getRatingCount();
		double ratings = ((movie.getRating()*ratingHead)+ rating.getRating())/(ratingHead+1);
		
		String sql = "UPDATE MOVIE SET RATING_HEAD=? , RATING_COUNT=? WHERE MOVIE_ID=?";
		jdbcTemplate.update(sql,ratingHead+1,ratings,rating.getMovieId());
		
	}
	public List<String> getBookedTickets(BookTickets availableTickets) {
		String sql = "SELECT SEAT_NO FROM TICKET_BOOKING WHERE THEATRE_MOVIE_ID=? AND SHOW_TIME=? AND DATE=?";
		 return jdbcTemplate.query(sql, new Object[] {availableTickets.getTheatreMovieId(),availableTickets.getShowTime(), availableTickets.getShowDate()}, new StringMapper());
		}
}
