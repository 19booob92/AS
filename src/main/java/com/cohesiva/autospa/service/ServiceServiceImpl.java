package com.cohesiva.autospa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cohesiva.autospa.dao.ServiceDAO;
import com.cohesiva.autospa.model.Service;

public class ServiceServiceImpl implements ServiceService {

	@Autowired
	ServiceDAO serviceDAO;
	
	@Override
	public void addService(Service service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateService(Service service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Service getService(Long id) {
		return serviceDAO.getService(id);
	}

	@Override
	public void deleteService(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Service> getServices() {
		return serviceDAO.getServices();
	}

}
