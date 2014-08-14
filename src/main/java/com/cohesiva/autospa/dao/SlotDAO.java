package com.cohesiva.autospa.dao;

import java.util.List;

import com.cohesiva.autospa.model.Card;
import com.cohesiva.autospa.model.Slot;

public interface SlotDAO {
	
	public void addSlot(Slot slot);

	public void updateSlot(Slot slot);

	public Slot getSlot(Long id);

	public void deleteSlot(Long id);

	public List<Slot> getSlots();
}
