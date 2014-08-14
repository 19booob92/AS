package com.cohesiva.autospa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cohesiva.autospa.model.Card;
import com.cohesiva.autospa.model.Slot;

@Repository
public class SlotDAOImpl implements SlotDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addSlot(Slot slot) {
	}

	@Override
	public void updateSlot(Slot slot) {
	}

	@Override
	public Slot getSlot(Long id) {
		return (Slot) getCurrentSession().get(Slot.class, id);
	}

	@Override
	public void deleteSlot(Long id) {
	}

	@Override
	public List<Slot> getSlots() {
		return getCurrentSession().createQuery("from Slot").list();
	}
	
}
