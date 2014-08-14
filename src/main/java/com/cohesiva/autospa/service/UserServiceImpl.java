package com.cohesiva.autospa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.cohesiva.autospa.dao.UserDAO;
import com.cohesiva.autospa.model.User;

@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void addUser(User user) {
		userDAO.addUser(user);		
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public User getUser(Long id) {
		return userDAO.getUser(id);
	}

	@Override
	public void deleteUser(Long id) {
		userDAO.deleteUser(id);
	}

	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	public UserDetails loadUserByUsername(String id)
			throws UsernameNotFoundException {
		//FIXME
		System.out.println(id + "  " + userDAO.getUser( Long.getLong(id)));
		return (UserDetails) userDAO.getUser( Long.getLong(id) );
	}

}
