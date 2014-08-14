package com.cohesiva.autospa.service;

import java.util.List;

import com.cohesiva.autospa.model.Service;

public interface ServiceService {
	
	public void addService(Service service);

	public void updateService(Service service);

	public Service getService(Long id);

	public void deleteService(Long id);

	public List<Service> getServices();
}
