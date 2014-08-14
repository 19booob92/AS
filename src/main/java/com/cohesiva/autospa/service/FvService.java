package com.cohesiva.autospa.service;

import java.util.List;

import com.cohesiva.autospa.model.FV;

public interface FvService {
	
	public void addFv(FV fv);

	public void updateFv(FV fv);

	public FV getFv(Long id);

	public void deleteFv(Long id);

	public List<FV> getFvs();
}
