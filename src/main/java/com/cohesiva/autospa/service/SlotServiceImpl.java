package com.cohesiva.autospa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cohesiva.autospa.dao.SlotDAO;
import com.cohesiva.autospa.model.Card;
import com.cohesiva.autospa.model.Slot;

@Service
@Transactional
public class SlotServiceImpl implements SlotService{

	@Autowired
	private SlotDAO slotDAO;
	
	@Override
	public void addSlot(Slot slot) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateSlot(Slot slot) {
		// TODO Auto-generated method stub
	}

	@Override
	public Slot getSlot(Long id) {
		return slotDAO.getSlot(id);
	}

	@Override
	public void deleteSlot(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Slot> getSlots() {
		return slotDAO.getSlots();
	}
}
