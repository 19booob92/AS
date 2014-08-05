package com.autospa.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.bind.annotation.XmlRootElement;

import com.autospa.utils.FilesOperations;
import com.autospa.utils.ProtocolProperties;
import com.autospa.utils.ServerProperties;

public class ClientController implements Runnable {

	private String name;
	private Socket socketClient;
	private DataInputStream stdIn;
	private DataOutputStream stdOut;
	private byte[] messageData;
	private Server server;
	private int keepAliveCounter = 0;
	ClientModel clientModel = new ClientModel();
	
	
	public ClientController(Socket socket, Server server) {
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
				System.out.println("Oczekiwanie na klient√≥w...");
				// getIdentifyMessage();
				getKeepAlive();
			}
		} catch (IOException ex) {
			clientModel.setAvaliable(false);
		}
	}

	private void getIdentifyMessage() throws IOException {

		messageData = new byte[50];
		stdIn.read(messageData);
		System.err.println("Dosta≈Çem " + Arrays.toString(messageData));
		switch (messageData[ProtocolProperties.SECOND_MSG_BYTE]) {
		case (byte) 1: {
			FilesOperations.saveDataToFile(System.currentTimeMillis()
					+ "   Grupa: Identyfikacja myjni, numer protoko≈Çu  "
					+ messageData[4] + messageData[5] + messageData[6]
					+ messageData[7] + "\n");
		}
			break;
		case (byte) 0x02: {
			// FIXME
		}
			break;
		case (byte) 0x03: {
			// FIXME
		}
			break;
		case (byte) 0x04: {
			// FIXME
		}
			break;
		default: {
			// FIXME
		}
		}
	}

	private void getKeepAlive() throws IOException {
		messageData = new byte[ProtocolProperties.FIVE_BYTES_MESSAGE];
		stdIn.read(messageData);
		byte tmp = messageData[ProtocolProperties.FIVE_BYTES_MESSAGE - 1];
		System.err.println("cala wiadomosc KA" + Arrays.toString(messageData));
		messageData[ProtocolProperties.FIVE_BYTES_MESSAGE - 1] = (byte) (tmp + 1);
		++keepAliveCounter;
		
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

//		int count = stdIn.read(messageData);
		System.err.println("cala wiadomosc monety" + Arrays.toString(messageData));
		System.err.println(messageData[4] +  "  " + messageData[5] );
		FilesOperations.saveDataToFile(System.currentTimeMillis()
				+ "   Grupa: cykliczne dane finansowe, ilo≈õƒá monet 1 z≈Ç  "
				+ messageData[5] + "\n");
		clientModel.setOnePLNCoins(messageData[4]);
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
				System.err.println(keepAliveCounter);
				try {
					 System.err.println("wysylam");
					 sendOnePLNCoinsCountRequest();
				 } catch (IOException ex) {
					 System.err.print(ex+"nie uda≥o siÍü");
				 }
				if (keepAliveCounter < 3) {
					 clientModel.setAvaliable(false);
				} else {
					clientModel.setAvaliable(true);
				}
				keepAliveCounter = 0;
			}
		}, ServerProperties.TIME_PERIOD, ServerProperties.TIME_PERIOD);

		getOnePLNCoinsState.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				 try {
					 System.err.println("wysylam");
					 sendOnePLNCoinsCountRequest();
				 } catch (IOException ex) {
					 System.err.print(ex+"nie uda≥o siÍü");
				 }
			}
		}, ServerProperties.GET_ONE_PLN_STATE_TIME_PERIOD, ServerProperties.GET_ONE_PLN_STATE_TIME_PERIOD);
		
	}
	
	public ClientModel getNewClient() {

		clientModel.setName(this.socketClient.getInetAddress().getHostAddress());
		return clientModel;
	}

	public Socket getSocketClient() {
		return socketClient;
	}

	public void setSocketClient(Socket socketClient) {
		this.socketClient = socketClient;
	}

	public byte[] getMessageData() {
		return messageData;
	}

	public void setMessageData(byte[] messageData) {
		this.messageData = messageData;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
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