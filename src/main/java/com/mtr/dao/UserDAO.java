package com.mtr.dao;

import java.util.List;

import javax.sql.DataSource;

import com.mtr.pojo.BookedTicketsForParticularShow;
import com.mtr.pojo.Ratings;
import com.mtr.pojo.Ticket;
import com.mtr.pojo.User;

public interface UserDAO {
	
	void setDataSource(DataSource dataSource);

	public boolean register(User user);
	
	public boolean editUserDetails(User user);
	
	public User login(String name, String password);
		
	public boolean addTicket(Ticket ticket);
	
	public boolean updateRatings(Ratings rating);
	
	public List<String> getBookedTickets(BookedTicketsForParticularShow bookedTickets);
	
}
