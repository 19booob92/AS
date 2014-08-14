package com.cohesiva.autospa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cohesiva.autospa.model.Client;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client getClient(Long id) {
		return (Client) getCurrentSession().get(Client.class, id);
	}

	@Override
	public void deleteClient(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> getClients() {
		return getCurrentSession().createQuery("from Client").list();
	}

}
