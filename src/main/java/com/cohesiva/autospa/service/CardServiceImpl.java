package com.cohesiva.autospa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cohesiva.autospa.dao.CardDAO;
import com.cohesiva.autospa.dao.CardDAO;
import com.cohesiva.autospa.model.Card;
import com.cohesiva.autospa.model.Card;

@Service
@Transactional
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDAO cardDAO;

	@Override
	public void addCard(Card card) {
		cardDAO.addCard(card);
	}

	@Override
	public void updateCard(Card card) {
		cardDAO.updateCard(card);
	}

	@Override
	public Card getCard(Long id) {
		return cardDAO.getCard(id);
	}

	@Override
	public void deleteCard(Long id) {
		cardDAO.deleteCard(id);
	}

	@Override
	public List<Card> getCards() {
		return cardDAO.getCards();
	}

}
