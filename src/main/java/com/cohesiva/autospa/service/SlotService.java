package com.cohesiva.autospa.service;

import java.util.List;

import com.cohesiva.autospa.model.Slot;

public interface SlotService {
	
	public void addSlot(Slot slot);

	public void updateSlot(Slot slot);

	public Slot getSlot(Long id);

	public void deleteSlot(Long id);

	public List<Slot> getSlots();
}
