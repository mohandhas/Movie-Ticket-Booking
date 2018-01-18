package com.mtr.dao;

import java.util.List;

import javax.sql.DataSource;

import com.mtr.pojo.BookTickets;
import com.mtr.pojo.Movie;
import com.mtr.pojo.Ratings;
import com.mtr.pojo.Ticket;
import com.mtr.pojo.User;

public interface UserDAO {
	
	void setDataSource(DataSource dataSource);

	public boolean register(User user);
	
	public User login(String name, String password);
	
	public List<Movie> getMoviesInTheatre(int id);
	
	public void addTicket(Ticket ticket);
	
	public void updateRatings(Ratings rating);
	
	public List<String> getBookedTickets(BookTickets availableTickets);
	
}
