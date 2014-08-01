package com.autospa.main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autospa.utils.ServerProperties;

public class Server {
	private ServerSocket serverSocket;
	private int port;
	private int kaCounter = 0;
	private Thread clientCommunicationThread;
	private List<Client> clientsList;

	private Client simpleClient;

	public Server() {
		this.clientsList = new ArrayList<Client>();
		this.port = ServerProperties.PORT_NUMBER;
	}

	public void startServer(String ip) throws IOException {
		serverSocket = new ServerSocket(port, port, InetAddress.getByName(ip));

		System.out.println("Uruchamianie serwera na porcie: " + port
				+ " i IP = " + ip);

		while (true) {
			try {
				simpleClient = new Client(serverSocket.accept(), this);
				clientsList.add(simpleClient);
				Thread client = new Thread(simpleClient);
				client.start();

			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}

	public List<Client> getClientsList() {
		return clientsList;
	}

	public void setClientsList(List<Client> clientsList) {
		this.clientsList = clientsList;
	}

	public Client getSimpleClient() {
		return simpleClient;
	}

	public void setSimpleClient(Client simpleClient) {
		this.simpleClient = simpleClient;
	}

}
