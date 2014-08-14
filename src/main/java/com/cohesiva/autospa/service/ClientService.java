package com.cohesiva.autospa.service;

import java.util.List;

import com.cohesiva.autospa.model.Client;

public interface ClientService {

	public void addClient(Client client);

	public void updateClient(Client client);

	public Client getClient(Long id);

	public void deleteClient(Long id);

	public List<Client> getClients();
	
}
