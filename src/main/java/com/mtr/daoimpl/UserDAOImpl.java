package com.mtr.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mtr.dao.UserDAO;
import com.mtr.mapper.AvailableTicketsMapper;
import com.mtr.mapper.MovieMapper;
import com.mtr.mapper.UserMapper;
import com.mtr.pojo.BookedTicketsForParticularShow;
import com.mtr.pojo.Movie;
import com.mtr.pojo.Ratings;
import com.mtr.pojo.Ticket;
import com.mtr.pojo.User;

public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}
	
	private User findUserId(String phone)
	{
		String sql = "SELECT * FROM USER WHERE PHONE=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {phone}, new UserMapper());
	}
	
	public void register(User user) {
		
		String sql = "INSERT INTO USER (USER_NAME,EMAIL,PASSWORD,PHONE) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getPassword(),user.getPhone());
		
		User getId= findUserId(user.getPhone());
				
		for(int i=0;i<user.getFavoriteGenre().size();i++)
		{
			String sql2 = "INSERT INTO USER_GENRE(USER_ID, GENRE_ID) VALUES(?,?)";
			jdbcTemplate.update(sql2,getId.getId(),user.getFavoriteGenre().get(i));
		}
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
	
	public void addTicket(Ticket ticket) {
		String sql = "INSERT INTO TICKET_BOOKING(THEATRE_MOVIE_ID,EMAIL, SEAT_NO, DATE,SHOW_TIME) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql,ticket.getTheatreMovieId(),ticket.getEmail(),ticket.getSeatNumber(),ticket.getDate(),ticket.getShowTime());
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
	
	public List<String> getBookedTickets(BookedTicketsForParticularShow bookedTickets) {
		String sql = "SELECT SEAT_NO FROM TICKET_BOOKING WHERE THEATRE_MOVIE_ID=? AND SHOW_TIME=? AND DATE=?";
		 return jdbcTemplate.query(sql, new Object[] {bookedTickets.getTheatreMovieId(),bookedTickets.getShowTime(), bookedTickets.getShowDate()}, new AvailableTicketsMapper());
		}
}
