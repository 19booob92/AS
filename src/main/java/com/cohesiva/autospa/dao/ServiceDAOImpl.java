package com.cohesiva.autospa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cohesiva.autospa.model.Service;

@Repository
public class ServiceDAOImpl implements ServiceDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addService(Service service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateService(Service service) {
		// TODO Auto-generated method stub

	}

	@Override
	public Service getService(Long id) {
		return (Service) getCurrentSession().get(Service.class, id);
	}

	@Override
	public void deleteService(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Service> getServices() {
		return getCurrentSession().createQuery("Service").list();
	}

}
