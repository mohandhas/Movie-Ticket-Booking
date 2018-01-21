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

	private User findUserId(String phone) {
		User checker;
		try {
			String sql = "SELECT * FROM USER WHERE PHONE=?";
			checker = jdbcTemplate.queryForObject(sql, new Object[] { phone }, new UserMapper());
		} catch (Exception e) {
			checker = null;
		}

		if (checker == null) {
			return checker;
		}
		return checker;
	}

	public boolean register(User user) {

		int checker1, checker2 = 0;
		try {
			String sql = "INSERT INTO USER (USER_NAME,EMAIL,PASSWORD,PHONE) VALUES (?,?,?,?)";
			checker1 = jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getPhone());

			User getId = findUserId(user.getPhone());
			if (getId == null) {
				return false;
			}
			for (int i = 0; i < user.getFavoriteGenre().size(); i++) {
				String sql2 = "INSERT INTO USER_GENRE(USER_ID, GENRE_ID) VALUES(?,?)";
				checker2 += jdbcTemplate.update(sql2, getId.getId(), user.getFavoriteGenre().get(i));
			}
		} catch (Exception e) {
			checker1 = 0;
			checker2 = 0;
		}

		if (checker1 == 0 && checker2 == 0) {
			return false;
		}
		return true;
	}

	private User findUser(String email) {
		User checker;
		try {
			String sql = "SELECT * FROM USER WHERE EMAIL=?";
			checker = jdbcTemplate.queryForObject(sql, new Object[] { email }, new UserMapper());
		} catch (Exception e) {
			checker = null;
		}
		return checker;
	}

	public User login(String email, String password) {
		User user = this.findUser(email);
		if (user == null) {
			return null;
		}
		if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	public boolean addTicket(Ticket ticket) {
		int checker;
		try {
			String sql = "INSERT INTO TICKET_BOOKING(THEATRE_MOVIE_ID,EMAIL, SEAT_NO, DATE,SHOW_TIME) VALUES(?,?,?,?,?)";
			checker = jdbcTemplate.update(sql, ticket.getTheatreMovieId(), ticket.getEmail(), ticket.getSeatNumber(),
					ticket.getDate(), ticket.getShowTime());
		} catch (Exception e) {
			checker = 0;
		}

		if(checker == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean editUserDetails(User user) {
		int checker1, checker2 = 0;
		try {
			String sql = "UPDATE USER SET USER_NAME = ?, PASSWORD = ?,PHONE = ? WHERE EMAIL=?";
			checker1 = jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getPhone(),user.getEmail());

			User getId = findUserId(user.getPhone());
			if (getId == null) {
				return false;
			}
			for (int i = 0; i < user.getFavoriteGenre().size(); i++) {
				String sql2 = "UPDATE USER_GENRE SET GENRE_ID=? WHERE USER_ID=?";
				checker2 += jdbcTemplate.update(sql2,  user.getFavoriteGenre().get(i), getId.getId());
			}
		} catch (Exception e) {
			checker1 = 0;
			checker2 = 0;
		}

		if (checker1 == 0 && checker2 == 0) {
			return false;
		}
		return true;
	}
	
	private Movie getMovie(int id) {
		Movie checker;
		try {
			String sql = "SELECT * FROM MOVIE WHERE MOVIE_ID=?";
			checker = jdbcTemplate.queryForObject(sql, new Object[] { id }, new MovieMapper());
		} catch (Exception e) {
			checker = null;
		}
		return checker;
	}

	public boolean updateRatings(Ratings rating) {
		int checker = 0;
		try {
			Movie movie = getMovie(rating.getMovieId());
			if (movie == null) {
				return false;
			}
			int ratingHead = movie.getRatingCount();
			double ratings = ((movie.getRating() * ratingHead) + rating.getRating()) / (ratingHead + 1);

			String sql = "UPDATE MOVIE SET RATING_HEAD=? , RATING_COUNT=? WHERE MOVIE_ID=?";
			jdbcTemplate.update(sql, ratingHead + 1, ratings, rating.getMovieId());
		} catch (Exception e) {
			checker = 0;
		}
		if (checker == 0) {
			return false;
		}
		return true;
	}

	public List<String> getBookedTickets(BookedTicketsForParticularShow bookedTickets) {
		List<String> list;
		try {
		String sql = "SELECT SEAT_NO FROM TICKET_BOOKING WHERE THEATRE_MOVIE_ID=? AND SHOW_TIME=? AND DATE=?";
		list = jdbcTemplate.query(sql, new Object[] { bookedTickets.getTheatreMovieId(), bookedTickets.getShowTime(),
				bookedTickets.getShowDate() }, new AvailableTicketsMapper());
		}
		catch (Exception e) {
			list=null;
		}
		
		return list;
	}
}
