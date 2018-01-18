package com.mtr.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mtr.daoimpl.UserDAOImpl;
import com.mtr.pojo.BookTickets;
import com.mtr.pojo.Ratings;
import com.mtr.pojo.Ticket;
import com.mtr.pojo.User;

@SessionAttributes("activeuser")
@RestController
public class UserController {
	
	@Autowired
	UserDAOImpl userDAOImpl;

	@RequestMapping(value= "addUser", method=RequestMethod.POST)
	public void addUser(@RequestBody User user)
	{		
			userDAOImpl.register(user);
			
	}
	
	@RequestMapping(value= "login", method=RequestMethod.POST)
	public void validateUser(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session)
	{		
			User user = userDAOImpl.login(email, password);
			if(user==null)
			{
				System.out.println("NO"); 
			}
			else
			{
				session.setAttribute("activeUser", email);
			}
	}
	
	@RequestMapping(value= "addTicket", method=RequestMethod.POST)
	public void addTicket(@RequestBody Ticket ticket)
	{		
			userDAOImpl.addTicket(ticket);
	}
	
	@RequestMapping(value= "ratings", method=RequestMethod.POST)
	public void ratings(@RequestBody Ratings rating)
	{
		System.out.println(rating);
		userDAOImpl.updateRatings(rating);
	}
	
	@RequestMapping(value= "bookedTickets", method=RequestMethod.POST)
	public List<String> getBookedTickets(@RequestBody BookTickets bookedTickets)
	{
		return userDAOImpl.getBookedTickets(bookedTickets);
	}
}
