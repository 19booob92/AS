package com.autospa.controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autospa.models.ClientModel;
import com.autospa.properties.ServerProperties;

public class ServerController {
	private ServerSocket serverSocket;
	private int port;
	private int kaCounter = 0;
	private Thread clientCommunicationThread;
	private boolean isRunning;
	private ClientController simpleClient;

	private List<ClientModel> clientsList;

	
	public ServerController() {
		this.clientsList = new ArrayList<ClientModel>();
		this.port = ServerProperties.PORT_NUMBER;
	}

	public void startServer(String ip) throws IOException {
		serverSocket = new ServerSocket(port, port, InetAddress.getByName(ip));
		isRunning = true;
		System.out.println("Uruchamianie serwera na porcie: " + port
				+ " i IP = " + ip);

		while (isRunning) {
			try {
				simpleClient = new ClientController(serverSocket.accept(), this);
				addToList();
				Thread client = new Thread(simpleClient);
				client.start();

			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}

	public List<ClientModel> getClientsList() {
		return clientsList;
	}

	public void setClientsList(List<ClientModel> clientsList) {
		this.clientsList = clientsList;
	}

	public ClientController getSimpleClient() {
		return simpleClient;
	}

	public void setSimpleClient(ClientController simpleClient) {
		this.simpleClient = simpleClient;
	}

	public void addToList() {
		ClientModel tmpClientModel = simpleClient.getNewClient();
		int indexOfItem = clientsList.indexOf(tmpClientModel);
		if (indexOfItem >= 0) {
			tmpClientModel.setId(clientsList.get(indexOfItem).getId());
			clientsList.remove(indexOfItem);
			clientsList.add(indexOfItem, tmpClientModel);
		} else {
			clientsList.add(tmpClientModel);
		}
	}
	
	public void stopServer() {
		try {
			isRunning  = false;
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
