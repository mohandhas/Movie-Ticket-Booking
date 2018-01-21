package com.mtr.controllers;

/*
 * 
 * 
 */

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
import com.mtr.dao.AdminDAO;
import com.mtr.pojo.Addons;
import com.mtr.pojo.BookedTicketsForParticularShow;
import com.mtr.pojo.GetMovieInTheatre;
import com.mtr.pojo.Movie;
import com.mtr.pojo.MoviesListInTheatre;
import com.mtr.pojo.Theatre;
import com.mtr.pojo.MovieInTheatre;

@SessionAttributes("activeAdmin")
@RestController
public class AdminController {

	@Autowired
	AdminDAO adminDAO;

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "adminLogin", method = RequestMethod.POST)
	public void validateAdmin(@RequestParam("id") String id, @RequestParam("password") String password,
			HttpSession session) {
		if (!adminDAO.login(id, password)) {
			throw new CustomizedBadRequestException("USER NAME OR PASSWORD INVALID");
		}
		session.setAttribute("activeAdmin", id);
	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "addTheatre", method = RequestMethod.POST)
	public void addTheatre(@RequestBody Theatre theatre, HttpSession session) {
		if (session.getAttribute("activeAdmin") != null) {
			if (!adminDAO.addTheatre(theatre)) {
				throw new CustomizedBadRequestException("DETAILS ENTERED IS INVALID");
			}
			throw new CustomizedBadRequestException("Login First!x");
		}
	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "addMovie", method = RequestMethod.POST)
	public void addMovie(@RequestBody Movie movie, HttpSession session) {
		if (session.getAttribute("activeAdmin") != null) {
			if (!adminDAO.addMovie(movie)) {
				throw new CustomizedBadRequestException("DETAILS ENTERED IS INVALID");
			}

			throw new CustomizedBadRequestException("Login First!x");
		}
	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "addAddons", method = RequestMethod.POST)
	public void addAddons(@RequestBody Addons addon, HttpSession session) {
		if (session.getAttribute("activeAdmin") != null) {
			if (!adminDAO.addAddon(addon)) {
				throw new CustomizedBadRequestException("DETAILS ENTERED IS INVALID!");
			}
			throw new CustomizedBadRequestException("Login First!x");
		}
	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "addMovieInTheatre", method = RequestMethod.POST)
	public void addMovieInTheatre(@RequestBody MovieInTheatre theatreMovie, HttpSession session) {
		if (session.getAttribute("activeAdmin") != null) {
			if (!adminDAO.addMovieInTheatre(theatreMovie)) {
				throw new CustomizedBadRequestException("DETAILS ENTERED IS INVALID!");
			}
			throw new CustomizedBadRequestException("Login First!x");
		}
	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "listTheatres", method = RequestMethod.GET)
	public List<Theatre> listTheatres(HttpSession session) {
		List<Theatre> list = adminDAO.getAllTheatres();
		if (session.getAttribute("activeAdmin") != null) {
			if (list == null) {
				throw new CustomizedNotFoundException("No theatres found");
			}
			return list;
		}
		throw new CustomizedBadRequestException("Login First!x");
	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "listMovies", method = RequestMethod.GET)
	public List<Movie> listMovies(HttpSession session) {
		List<Movie> list = adminDAO.getAllMovies();
		if (session.getAttribute("activeAdmin") != null) {
			if (list == null) {
				throw new CustomizedNotFoundException("No movies found");
			}
			return list;
		}
		throw new CustomizedBadRequestException("Login First!x");
	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "listAddons", method = RequestMethod.GET)
	public List<Addons> listAddons(HttpSession session) {
		List<Addons> list = adminDAO.getAllAddons();
		if (session.getAttribute("activeAdmin") != null) {
			if (list == null) {
				throw new CustomizedNotFoundException("No Addons found");
			}
			return list;
		}
		throw new CustomizedBadRequestException("Login First!x");

	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "getMovieInTheatre", method = RequestMethod.POST)
	public List<MoviesListInTheatre> listMoviesInTheatre(@RequestBody GetMovieInTheatre getMoviesInTheatre,
			HttpSession session) {
		List<MoviesListInTheatre> list = adminDAO.listMoviesInTheatre(getMoviesInTheatre);
		if (session.getAttribute("activeAdmin") != null) {
			if (list == null) {
				throw new CustomizedNotFoundException("No movies found");
			}
			return list;
		}
		throw new CustomizedBadRequestException("Login First!x");

	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "editMovieInTheatre", method = RequestMethod.POST)
	public void editMovieInTheatre(@RequestBody MovieInTheatre theatreMovie, HttpSession session) {
		if (session.getAttribute("activeAdmin") != null) {
			adminDAO.editMovieInTheatre(theatreMovie);
		}
		throw new CustomizedBadRequestException("Login First!x");

	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "editAddon", method = RequestMethod.PUT)
	public void editAddonCost(@RequestBody Addons addon, HttpSession session) {
		if (session.getAttribute("activeAdmin") != null) {
			adminDAO.editAddonCost(addon);
		}
		throw new CustomizedBadRequestException("Login First!x");

	}

	/*
	 * 
	 * 
	 */
	@RequestMapping(value = "getTicketCount", method = RequestMethod.POST)
	public int getTicketCount(@RequestBody BookedTicketsForParticularShow bookedTicketsForParticularShow,
			HttpSession session) {
		if (session.getAttribute("activeAdmin") != null) {
			int checker = adminDAO.getTicketCountInAParticularShow(bookedTicketsForParticularShow);
			return checker;
		}
		throw new CustomizedBadRequestException("Login First!x");

	}

}
