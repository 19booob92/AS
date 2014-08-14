package com.cohesiva.autospa.dao;

import java.util.List;

import com.cohesiva.autospa.model.FV;


public interface FvDAO {
	public void addFv(FV fv);

	public void updateFv(FV fv);

	public FV getFv(Long id);

	public void deleteFv(Long id);

	public List<FV> getFvs();
}
