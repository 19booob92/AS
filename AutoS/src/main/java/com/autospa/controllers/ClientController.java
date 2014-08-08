package com.autospa.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.ArrayUtils;

import com.autospa.models.ClientModel;
import com.autospa.properties.ProtocolProperties;
import com.autospa.properties.ServerProperties;
import com.autospa.utils.FilesOperations;
import com.autospa.utils.MessageRecognizer;

public class ClientController implements Runnable {

	private Socket socketClient;
	private DataInputStream stdIn;
	private DataOutputStream stdOut;
	private byte[] headerData = new byte[ProtocolProperties.HEADER_LENGTH];
	private byte[] data;
	private ServerController server;
	private int keepAliveCounter = 0;
	private String coinValue;
	private MessageRecognizer messageRecognizer;
	
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
			messageRecognizer = new MessageRecognizer(this, clientModel, stdIn);
			setScheduler();
			while (server.isRunning()) {
				System.out.println("Oczekiwanie na klientow...");
				// getIdentifyMessage();
				getMessage();
			}
		} catch (IOException ex) {
			clientModel.setAvaliable(false);
			keepAliveCounter = 0;
		}
	}

	private void getMessage() throws IOException {
		stdIn.read(headerData);
		messageRecognizer.checkMessage(headerData);
	}
	
	public void setPeriodValueInterval() throws IOException {
//		FIXME
		data = new byte[ProtocolProperties.SIX_BYTES_MSG];
		stdIn.read(data);

		FilesOperations.saveDataToFile(System.currentTimeMillis()
				+ "   Grupa: POTWIERDZENIE_I_STEROWANIE  interwa³ wartoœci cyklicznej " + Arrays.toString(data)
				+ "\n");

		sendMessage(ProtocolProperties.OK_ACK_MSG);
	}
	
	public void setServerIPAddress() throws IOException {
//		FIXME
		data = new byte[4];
		stdIn.read(data);

		FilesOperations.saveDataToFile(System.currentTimeMillis()
				+ "   Grupa: POTWIERDZENIE_I_STEROWANIE  zmiana adresu IP serwera " + Arrays.toString(data)
				+ "\n");

		sendMessage(ProtocolProperties.OK_ACK_MSG);
	}
		
	public void sendKeepAlive() throws IOException {
		data = new byte[ProtocolProperties.ONE_BYTE_MSG];
		
		stdIn.read(data);
		++keepAliveCounter;
		byte tmp = data[0];
		data[0] = (byte) (tmp + 1);

		FilesOperations.saveDataToFile(System.currentTimeMillis()
				+ "   Grupa: POTWIERDZENIE_I_STEROWANIE  KEEP_ALIVE " + data[0]
				+ "\n");

		sendMessage(headerData, data);
	}

	public void sendMessage(byte[] message, byte[] data) throws IOException {
		byte[] concanateMessage = ArrayUtils.addAll(message, data);
		stdOut.write(concanateMessage);
		stdOut.flush();
	}

	public void sendMessage(byte[] message) throws IOException {
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
				if (keepAliveCounter < 1) {
					clientModel.setAvaliable(false);
					FilesOperations.saveDataToFile("Alaaarrmmmm!!!!");
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