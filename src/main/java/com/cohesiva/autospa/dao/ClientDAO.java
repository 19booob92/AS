package com.cohesiva.autospa.dao;

import java.util.List;

import com.cohesiva.autospa.model.Client;

public interface  ClientDAO {
	
	public void addClient(Client client);

	public void updateClient(Client client);

	public Client getClient(Long id);

	public void deleteClient(Long id);

	public List<Client> getClients();
}
