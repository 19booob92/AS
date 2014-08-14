package com.cohesiva.autospa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cohesiva.autospa.model.FV;

@Repository
public class FvDAOImpl implements FvDAO {

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired
	private SessionFactory sessionFactory; 
	
	@Override
	public void addFv(FV fv) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateFv(FV fv) {
		// TODO Auto-generated method stub
	}

	@Override
	public FV getFv(Long id) {
		return (FV) getCurrentSession().get(FV.class, id);
	}

	@Override
	public void deleteFv(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<FV> getFvs() {
		return getCurrentSession().createQuery("from FV").list();
	}

}
