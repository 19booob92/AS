package com.autospa.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import com.autospa.utils.FilesOperations;
import com.autospa.utils.ProtocolProperties;
import com.autospa.utils.ServerProperties;

public class Client implements Runnable {

	private String name;
	private Socket socketClient;
	private DataInputStream stdIn;
	private DataOutputStream stdOut;
	private byte[] messageData;
	private static int iterator = 0;
	private final int id;
	private Server server;
	
	public Client(Socket socket, Server server) {
		this.name = name;
		id = iterator++;
		this.socketClient = socket;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			stdIn = new DataInputStream(socketClient.getInputStream());
			stdOut = new DataOutputStream(socketClient.getOutputStream());
			System.err.println("nowy " + id);
			this.setName("Nazwa myjni");
			while (true) {
				System.out.println("Oczekiwanie na klientów...");
				// getIdentifyMessage();
				getKeepAlive();
				// setScheduler();
			}
		} catch (IOException ex) {
			server.getClientsList().remove(this);
		}
	}

	private void getIdentifyMessage() throws IOException {

		messageData = new byte[50];
		stdIn.read(messageData);
		System.err.println("Dostałem " + Arrays.toString(messageData));
		switch (messageData[ProtocolProperties.SECOND_MSG_BYTE]) {
		case (byte) 1: {
			FilesOperations.saveDataToFile(System.currentTimeMillis()
					+ "   Grupa: Identyfikacja myjni, numer protokołu  "
					+ messageData[4] + messageData[5] + messageData[6]
					+ messageData[7] + "\n");
		}
			break;
		case (byte) 0x02: {

		}
			break;
		case (byte) 0x03: {

		}
			break;
		case (byte) 0x04: {

		}
			break;
		default: {

		}
		}
	}

	private void getKeepAlive() throws IOException {
		messageData = new byte[ProtocolProperties.FIVE_BYTES_MESSAGE];
		stdIn.read(messageData);

		byte tmp = messageData[ProtocolProperties.FIVE_BYTES_MESSAGE - 1];
		messageData[ProtocolProperties.FIVE_BYTES_MESSAGE - 1] = (byte) (tmp + 1);

		FilesOperations
				.saveDataToFile(System.currentTimeMillis()
						+ "   Grupa: POTWIERDZENIE_I_STEROWANIE  KEEP_ALIVE "
						+ messageData[ProtocolProperties.FIVE_BYTES_MESSAGE - 1]
						+ "\n");

		sendMessage(messageData);

	}

	public void sendOnePLNCoinsCountRequest() throws IOException {
		messageData = new byte[ProtocolProperties.SIX_BYTES_MESSAGE];
		sendMessage(new byte[] { ProtocolProperties.NULL, (byte) 0x02,
				ProtocolProperties.NULL, (byte) 0x01, ProtocolProperties.NULL,
				ProtocolProperties.NULL });

		int count = stdIn.read(messageData);
		FilesOperations.saveDataToFile(System.currentTimeMillis()
				+ "   Grupa: cykliczne dane finansowe, ilość monet 1 zł  "
				+ messageData[5] + "\n");
	}

	private void sendMessage(byte[] message) throws IOException {
		stdOut.write(message);
		stdOut.flush();
	}

	private void setScheduler() {
		Timer scheduler = new Timer();
		scheduler.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					sendOnePLNCoinsCountRequest();
				} catch (IOException ex) {
					System.err.print(ex);
				}
			}
		}, ServerProperties.TIME_PERIOD, ServerProperties.TIME_PERIOD);
	}

	@Override
	public String toString() {
		return id + " " + name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}