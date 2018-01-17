package com.mtr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mtr.daoimpl.UserDAOImpl;
import com.mtr.pojo.Movie;
import com.mtr.pojo.User;


@RestController
public class UserController {
	
	@Autowired
	UserDAOImpl userDAOImpl;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value= "addUser", method=RequestMethod.POST)
	public void addUser(@RequestBody User user)
	{		
			if(!userDAOImpl.register(user))
			{
				System.out.println("email already exists");
			}
			else
			{
				System.out.println("INSERTED");
			}
	}
	
	@RequestMapping(value= "login", method=RequestMethod.POST)
	public void validateUser(@RequestParam("email") String email,@RequestParam("password") String password)
	{		
			User user = userDAOImpl.login(email, password);
			if(user==null)
			{
				System.out.println("NO"); 
			}
			else
			{
				System.out.println("yes");

			}
	}
	
	@RequestMapping(value= "moviesInTheatre/{id}", method=RequestMethod.POST)
	public List<Movie> getMoviesInTheatre(@PathVariable int id)
	{		
			return userDAOImpl.getMoviesInTheatre(id);
			
	}
	
}
