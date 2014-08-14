package com.cohesiva.autospa.service;

import java.util.List;

import com.cohesiva.autospa.model.User;

public interface UserService {
	
	public void addUser(User team);
	public void updateUser(User team);
	public User getUser(Long id);
	public void deleteUser(Long id);
	public List<User> getUsers();

}
