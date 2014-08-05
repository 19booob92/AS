package com.autospa.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.ArrayUtils;

import com.autospa.models.ClientModel;
import com.autospa.properties.ProtocolProperties;
import com.autospa.properties.ServerProperties;
import com.autospa.utils.FilesOperations;

public class ClientController implements Runnable {

	private Socket socketClient;
	private DataInputStream stdIn;
	private DataOutputStream stdOut;
	private byte[] headerData = new byte[ProtocolProperties.HEADER_LENGTH];
	private byte[] data;
	private ServerController server;
	private int keepAliveCounter = 0;
	private String coinValue;

	ClientModel clientModel = new ClientModel();

	public ClientController(Socket socket, ServerController server) {
		this.socketClient = socket;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			clientModel.setAvaliable(true);
			stdIn = new DataInputStream(socketClient.getInputStream());
			stdOut = new DataOutputStream(socketClient.getOutputStream());
			setScheduler();
			while (true) {
				System.out.println("Oczekiwanie na klientow...");
				// getIdentifyMessage();
				getMessage();
			}
		} catch (IOException ex) {
			clientModel.setAvaliable(false);
		}
	}

	private void getMessage() throws IOException {
		stdIn.read(headerData);
		if (headerData[ProtocolProperties.FIRST_GROUP_BYTE] == (byte) 0xFF
				&& headerData[ProtocolProperties.SECOND_GROUP_BYTE] == (byte) 0xFE) {
			if (headerData[ProtocolProperties.FIRST_MSG_BYTE] == (byte) 0x00
					&& headerData[ProtocolProperties.SECOND_MSG_BYTE] == (byte) 0x03) {

				sendKeepAlive();
			} else if (true) {

			} else if (true) {

			}

		} else if (headerData[ProtocolProperties.FIRST_GROUP_BYTE] == (byte) 0x00
				&& headerData[ProtocolProperties.SECOND_GROUP_BYTE] == (byte) 0x02) {

			sendOnePLNCoinsCountRequest();
		} else if (true) {

		} else if (true) {

		}
	}

	public void sendKeepAlive() throws IOException {
		data = new byte[1];

		stdIn.read(data);
		++keepAliveCounter;

		byte tmp = data[0];
		data[0] = (byte) (tmp + 1);

		FilesOperations.saveDataToFile(System.currentTimeMillis()
				+ "   Grupa: POTWIERDZENIE_I_STEROWANIE  KEEP_ALIVE " + data[0]
				+ "\n");

		sendMessage(headerData, data);
	}

	public void sendOnePLNCoinsCountRequest() throws IOException {
		data = new byte[2];
		stdIn.read(data);

		switch (headerData[ProtocolProperties.FIRST_MSG_BYTE]) {
		case (byte) 0x00:
			coinValue = "1 z³";
			break;
		case (byte) 0x01:
			coinValue = "2 z³";
			break;
		case (byte) 0x02:
			coinValue = "5 z³";
			break;
		}

		FilesOperations.saveDataToFile(System.currentTimeMillis()
				+ "   Grupa: CYKLICZNE_DANE_FINANSOWE iloœæ monet: " + data[0]
				+ ',' + data[1] + "  " + coinValue + " stanowisko :  "
				+ headerData[ProtocolProperties.SECOND_MSG_BYTE] + "\n");
		clientModel.setOnePLNCoins(data[0]);

		sendMessage(new byte[] { (byte) 0xFF, (byte) 0xFE, (byte) 0x00,
				(byte) 0x01 });
	}

	private void sendMessage(byte[] message, byte[] data) throws IOException {
		byte[] concanateMessage = ArrayUtils.addAll(message, data);
		stdOut.write(concanateMessage);
		stdOut.flush();
	}

	private void sendMessage(byte[] message) throws IOException {
		stdOut.write(message);
		stdOut.flush();
	}

	private void setScheduler() {
		Timer checkAvaliableScheduler = new Timer();
		Timer getOnePLNCoinsState = new Timer();

		checkAvaliableScheduler.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.err.println("KA Counter: " + keepAliveCounter);
				try {
					sendOnePLNCoinsCountRequest();
				} catch (IOException ex) {
					System.err.print(ex + "nie uda³o siê wyslac pytania");
				}
				if (keepAliveCounter < 3) {
					clientModel.setAvaliable(false);
				} else {
					clientModel.setAvaliable(true);
				}
				keepAliveCounter = 0;
			}
		}, ServerProperties.TIME_PERIOD, ServerProperties.TIME_PERIOD);
	}

	public ClientModel getNewClient() {

		clientModel
				.setName(this.socketClient.getInetAddress().getHostAddress());
		return clientModel;
	}

	public Socket getSocketClient() {
		return socketClient;
	}

	public void setSocketClient(Socket socketClient) {
		this.socketClient = socketClient;
	}

	public byte[] getMessageData() {
		return headerData;
	}

	public void setMessageData(byte[] messageData) {
		this.headerData = messageData;
	}

	public ServerController getServer() {
		return server;
	}

	public void setServer(ServerController server) {
		this.server = server;
	}

	public DataInputStream getStdIn() {
		return stdIn;
	}

	public void setStdIn(DataInputStream stdIn) {
		this.stdIn = stdIn;
	}

	public DataOutputStream getStdOut() {
		return stdOut;
	}

	public void setStdOut(DataOutputStream stdOut) {
		this.stdOut = stdOut;
	}
}