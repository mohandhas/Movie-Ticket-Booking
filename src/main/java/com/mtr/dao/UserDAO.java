package com.mtr.dao;

import java.util.List;

import javax.sql.DataSource;

import com.mtr.pojo.BookedTicketsForParticularShow;
import com.mtr.pojo.Ratings;
import com.mtr.pojo.Ticket;
import com.mtr.pojo.User;

public interface UserDAO {
	
	void setDataSource(DataSource dataSource);

	public void register(User user);
	
	public User login(String name, String password);
		
	public void addTicket(Ticket ticket);
	
	public void updateRatings(Ratings rating);
	
	public List<String> getBookedTickets(BookedTicketsForParticularShow bookedTickets);
	
}
