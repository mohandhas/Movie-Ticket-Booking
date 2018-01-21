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

import com.mtr.customizedexceptions.CustomizedBadRequestException;
import com.mtr.customizedexceptions.CustomizedNotFoundException;
import com.mtr.daoimpl.AdminDAOImpl;
import com.mtr.daoimpl.UserDAOImpl;
import com.mtr.pojo.BookedTicketsForParticularShow;
import com.mtr.pojo.GetMovieInTheatre;
import com.mtr.pojo.MoviesListInTheatre;
import com.mtr.pojo.Ratings;
import com.mtr.pojo.Theatre;
import com.mtr.pojo.Ticket;
import com.mtr.pojo.User;

@SessionAttributes("activeuser")
@RestController
public class UserController {

	@Autowired
	UserDAOImpl userDAOImpl;

	@Autowired
	AdminDAOImpl adminDAOImpl;

	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		if (!userDAOImpl.register(user)) {
			throw new CustomizedBadRequestException("Not Registered");
		}
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public User validateUser(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {
		User user = userDAOImpl.login(email, password);
		if (user == null) {
			throw new CustomizedBadRequestException("User Name Password Doesnot Match!");
		}
		session.setAttribute("activeUser", email);
		return user;
	}

	@RequestMapping(value = "editUser", method = RequestMethod.PUT)
	public void editUser(@RequestBody User user, HttpSession session) {
		if (session.getAttribute("activeUser") != null) {
			if (!userDAOImpl.editUserDetails(user)) {
				throw new CustomizedBadRequestException("User details edited!");
			}
		}
		throw new CustomizedBadRequestException("User Should LOGIN first to edit details!");
	}

	@RequestMapping(value = "userListTheatres", method = RequestMethod.GET)
	public List<Theatre> listTheatres() {
		List<Theatre> list = adminDAOImpl.getAllTheatres();
		if (list == null) {
			throw new CustomizedNotFoundException("No theatres found");
		}
		return list;
	}

	@RequestMapping(value = "userGetMovieInTheatre", method = RequestMethod.POST)
	public List<MoviesListInTheatre> listMoviesInTheatre(@RequestBody GetMovieInTheatre getMoviesInTheatre) {
		List<MoviesListInTheatre> list = adminDAOImpl.listMoviesInTheatre(getMoviesInTheatre);
		if (list == null) {
			throw new CustomizedNotFoundException("No movies found");
		}
		return list;
	}

	@RequestMapping(value = "bookedTickets", method = RequestMethod.POST)
	public List<String> getBookedTickets(@RequestBody BookedTicketsForParticularShow bookedTickets) {
		List<String> list = userDAOImpl.getBookedTickets(bookedTickets);
		if (list == null) {
			throw new CustomizedNotFoundException("No Booked Tickets!");
		}
		return list;
	}

	@RequestMapping(value = "ratings", method = RequestMethod.POST)
	public void ratings(@RequestBody Ratings rating) {
		if (!userDAOImpl.updateRatings(rating)) {
			throw new CustomizedBadRequestException("Ratings not updated!");
		}
	}

	@RequestMapping(value = "addTicket", method = RequestMethod.POST)
	public void addTicket(@RequestBody Ticket ticket) {
		if (!userDAOImpl.addTicket(ticket)) {
			throw new CustomizedBadRequestException("Tickeet not added!");
		}
	}

}
