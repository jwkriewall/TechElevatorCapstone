package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.UserDAO;
import com.techelevator.model.User;

@RestController
@CrossOrigin
public class UserController {
	
	private UserDAO UserDAO;
	
	UserController(UserDAO UserDAO){
		this.UserDAO = UserDAO;
	}
	
	@RequestMapping(path="/users", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		return UserDAO.updateUser(user);
	}
	
	@RequestMapping(path="/users", method = RequestMethod.GET)
	public List<User> getUser(@RequestBody User user) {
		return UserDAO.findAll();
	}

}
