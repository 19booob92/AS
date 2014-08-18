package com.cohesiva.autospa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String id)
			throws UsernameNotFoundException {

		System.out.println(id + "  " + userDAO.getUser(Long.getLong(id)));
		User user = userDAO.getUser(Long.getLong(id));

		List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());

		return buildUserForAuthentication(user, authorities);
	}
	
	private User buildUserForAuthentication (User user, List<GrantedAuthority> authorities) {
		return new User();
	}

	private List<GrantedAuthority> buildUserAuthority(String userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		setAuths.add(new SimpleGrantedAuthority(userRoles));

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);

		return Result;
	}

}
