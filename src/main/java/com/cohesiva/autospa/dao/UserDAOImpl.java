package com.cohesiva.autospa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cohesiva.autospa.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addUser(User team) {
		getCurrentSession().save(team);
	}

	public void updateUser(User userModel) {
		User userToUpdate = getUser(userModel.getId());
		userToUpdate.setImie(userModel.getImie());
		userToUpdate.setNazwisko(userModel.getNazwisko());
		getCurrentSession().update(userToUpdate);
		
	}

	public User getUser(Long id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}

	public void deleteUser(Long id) {
		User card = getUser(id);
		if (card != null) {
			getCurrentSession().delete(card);
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return getCurrentSession().createQuery("from User").list();
	}

}
