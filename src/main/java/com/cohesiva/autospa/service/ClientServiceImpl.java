package com.cohesiva.autospa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cohesiva.autospa.dao.ClientDAO;
import com.cohesiva.autospa.model.Card;
import com.cohesiva.autospa.model.Client;

public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDAO clientDAO;

	@Override
	public void addClient(Client client) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateClient(Client client) {
		// TODO Auto-generated method stub
	}

	@Override
	public Client getClient(Long id) {
		return clientDAO.getClient(id);
	}

	@Override
	public void deleteClient(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Client> getClients() {
		return clientDAO.getClients();
	}
	

}
