package com.cohesiva.autospa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cohesiva.autospa.model.User;
import com.cohesiva.autospa.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody boolean addinguser(@ModelAttribute User user) {
		try {
			userService.addUser(user);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	@RequestMapping(value="/list", produces = "application/json")
	public @ResponseBody List<User> listOfusers() {
		return userService.getUsers();
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edituserPage(@PathVariable Long id) {
		return "edit";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String edditinguser(@ModelAttribute User user, @PathVariable Integer id) {
		return "editById";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		String message = "User was successfully deleted.";
		return message;
	}

}
