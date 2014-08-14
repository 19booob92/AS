package com.cohesiva.autospa.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import com.cohesiva.autospa.model.CarWasher;
import com.cohesiva.autospa.properties.ServerProperties;
import com.cohesiva.autospa.utils.CarWasherManager;

public class ServerController {
	private ServerSocket serverSocket;
	private int port;
	private int kaCounter = 0;
	private Thread clientCommunicationThread;
	private boolean isRunning;
	private CarWasherManager simpleClient;

	private List<CarWasher> clientsList;

	public ServerController() {
		this.clientsList = new ArrayList<CarWasher>();
		this.port = ServerProperties.PORT_NUMBER;
	}

	public void startServer(String ip) throws IOException {
		serverSocket = new ServerSocket(port, port, InetAddress.getByName(ip));
		isRunning = true;
		System.out.println("Uruchamianie serwera na porcie: " + port
				+ " i IP = " + ip);

		while (isRunning) {
			try {
				simpleClient = new CarWasherManager(serverSocket.accept(), this);
				addToList();
				Thread client = new Thread(simpleClient);
				client.start();

			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}

	public List<CarWasher> getClientsList() {
		return clientsList;
	}

	public void setClientsList(List<CarWasher> clientsList) {
		this.clientsList = clientsList;
	}

	public CarWasherManager getSimpleClient() {
		return simpleClient;
	}

	public void setSimpleClient(CarWasherManager simpleClient) {
		this.simpleClient = simpleClient;
	}

	public void addToList() {
		CarWasher tmpClientModel = simpleClient.getNewClient();
		int indexOfItem = clientsList.indexOf(tmpClientModel);
		if (indexOfItem >= 0) {
			tmpClientModel.setId(clientsList.get(indexOfItem).getId());
			clientsList.remove(indexOfItem);
			clientsList.add(indexOfItem, tmpClientModel);
		} else {
			clientsList.add(tmpClientModel);
		}
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
