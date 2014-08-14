package com.cohesiva.autospa.dao;

import java.util.List;

import com.cohesiva.autospa.model.User;

public interface UserDAO {
	
	public void addUser(User user);
	public void updateUser(User user);
	public User getUser(Long id);
	public void deleteUser(Long id);
	public List<User> getUsers();

}
