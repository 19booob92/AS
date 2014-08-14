package com.cohesiva.autospa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cohesiva.autospa.dao.FvDAO;
import com.cohesiva.autospa.model.Card;
import com.cohesiva.autospa.model.FV;


@Service
@Transactional
public class FvServiceImpl implements FvService {

	@Autowired
	private FvDAO fvDAO;

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
		return fvDAO.getFv(id);
	}

	@Override
	public void deleteFv(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FV> getFvs() {
		return fvDAO.getFvs();
	}
}
