package com.cohesiva.autospa.dao;

import java.util.List;

import com.cohesiva.autospa.model.Card;

public interface CardDAO {
	public void addCard(Card card);

	public void updateCard(Card card);

	public Card getCard(Long id);

	public void deleteCard(Long id);

	public List<Card> getCards();
}
