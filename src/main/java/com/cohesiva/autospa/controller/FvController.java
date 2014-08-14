package com.cohesiva.autospa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cohesiva.autospa.model.FV;
import com.cohesiva.autospa.model.User;
import com.cohesiva.autospa.service.FvService;
import com.cohesiva.autospa.service.UserService;

@Controller
@RequestMapping(value="/fv")
public class FvController {
	
	@Autowired
	private FvService fvService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public void adduserPage() {
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public void addinguser(@ModelAttribute User user) {
	}
	
	@RequestMapping(value="/list", produces = "application/json")
	public @ResponseBody List<FV> listOfusers() {
		return fvService.getFvs();
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public void edituserPage(@PathVariable Long id) {
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public void edditinguser(@ModelAttribute User user, @PathVariable Integer id) {
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public void deleteUser(@PathVariable Long id) {
	}

}
