package com.cohesiva.autospa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cohesiva.autospa.model.Card;
import com.cohesiva.autospa.model.User;

@Repository
public class CardDAOImpl implements CardDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addCard(Card card) {
		getCurrentSession().save(card);		
	}

	@Override
	public void updateCard(Card card) {
		Card cardToUpdate = getCard(card.getId());
		cardToUpdate.setId(card.getId());
		cardToUpdate.setAktywna(card.isAktywna());
		getCurrentSession().update(cardToUpdate);
		
	}

	@Override
	public Card getCard(Long id) {
		Card card = (Card) getCurrentSession().get(Card.class, id);
		return card;
	}

	@Override
	public void deleteCard(Long id) {
		Card card = getCard(id);
		if (card != null) {
			getCurrentSession().delete(card);
		}
	}

	@Override
	public List<Card> getCards() {
		return getCurrentSession().createQuery("from Card").list();
	}

}
