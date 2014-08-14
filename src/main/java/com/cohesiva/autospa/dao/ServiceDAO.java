package com.cohesiva.autospa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cohesiva.autospa.model.Service;

@Repository
public interface ServiceDAO {
	
	public void addService(Service service);

	public void updateService(Service service);

	public Service getService(Long id);

	public void deleteService(Long id);

	public List<Service> getServices();
}
