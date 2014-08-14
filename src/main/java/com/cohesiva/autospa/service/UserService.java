package com.cohesiva.autospa.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cohesiva.autospa.model.User;

public interface UserService extends UserDetailsService {
	
	public void addUser(User team);
	public void updateUser(User team);
	public User getUser(Long id);
	public void deleteUser(Long id);
	public List<User> getUsers();

}
