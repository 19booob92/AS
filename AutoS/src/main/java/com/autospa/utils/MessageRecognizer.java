package com.autospa.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.autospa.controllers.ClientController;
import com.autospa.controllers.ServerController;
import com.autospa.models.ClientModel;
import com.autospa.properties.ProtocolProperties;

public class MessageRecognizer {

	private ClientController clientController;
	private ClientModel clientModel;
	private DataInputStream inputStream;

	private String coinValue;
	
	private byte[] data;

	public MessageRecognizer(ClientController clientController,
			ClientModel clientModel, DataInputStream inputStream) {
		this.clientController = clientController;
		this.clientModel = clientModel;
		this.inputStream = inputStream;
	}

	public void checkMessage(byte[] headerData) throws IOException {
		if (headerData[ProtocolProperties.FIRST_GROUP_BYTE] == (byte) 0xFF
				&& headerData[ProtocolProperties.SECOND_GROUP_BYTE] == (byte) 0xFE) {

			if (headerData[ProtocolProperties.FIRST_MSG_BYTE] == (byte) 0x00
					&& headerData[ProtocolProperties.SECOND_MSG_BYTE] == (byte) 0x03) {
				
				Date date = new Date(System.currentTimeMillis());
				System.err.println(System.currentTimeMillis());
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
				String dateFormatted = formatter.format(date);
				System.err.println(dateFormatted);
				clientModel.setLastActivity(dateFormatted);
				clientController.sendKeepAlive();
			} else if (headerData[ProtocolProperties.FIRST_MSG_BYTE] == (byte) 0x00
					&& headerData[ProtocolProperties.SECOND_MSG_BYTE] == (byte) 0x01) {

				System.err.println("Otrzymano sygna� OK-ACK");
			} else if (headerData[ProtocolProperties.FIRST_MSG_BYTE] == (byte) 0x00
					&& headerData[ProtocolProperties.SECOND_MSG_BYTE] == (byte) 0x02) {

				System.err.println("Otrzymano sygna� NACK");
			} else if (headerData[ProtocolProperties.FIRST_MSG_BYTE] == (byte) 0x01
					&& headerData[ProtocolProperties.SECOND_MSG_BYTE] == (byte) 0x00) {

				clientController.setPeriodValueInterval();
			} else if (headerData[ProtocolProperties.FIRST_MSG_BYTE] == (byte) 0x01
					&& headerData[ProtocolProperties.SECOND_MSG_BYTE] == (byte) 0x01) {

				clientController.setServerIPAddress();
			}
		} else if (headerData[ProtocolProperties.FIRST_GROUP_BYTE] == (byte) 0x00
				&& headerData[ProtocolProperties.SECOND_GROUP_BYTE] == (byte) 0x01) {

			if (headerData[ProtocolProperties.FIRST_MSG_BYTE] == (byte) 0x00
					&& headerData[ProtocolProperties.SECOND_MSG_BYTE] == (byte) 0x01) {

				setProtocolNo(headerData);
			} else if (headerData[ProtocolProperties.FIRST_MSG_BYTE] == (byte) 0x00
					&& headerData[ProtocolProperties.SECOND_MSG_BYTE] == (byte) 0x02) {

				setCarWasherName(headerData);
			} else if (headerData[ProtocolProperties.FIRST_MSG_BYTE] == (byte) 0x00
					&& headerData[ProtocolProperties.SECOND_MSG_BYTE] == (byte) 0x03) {

				setCarWasherNo(headerData);
			} else if (headerData[ProtocolProperties.FIRST_MSG_BYTE] == (byte) 0x00
					&& headerData[ProtocolProperties.SECOND_MSG_BYTE] == (byte) 0x04) {

				setStatesAmount(headerData);
			}
		} else if (headerData[ProtocolProperties.FIRST_GROUP_BYTE] == (byte) 0x00
				&& headerData[ProtocolProperties.SECOND_GROUP_BYTE] == (byte) 0x02) {
			sendOnePLNCoinsCountRequest(headerData);
		} else if (true) {

		} else if (true) {

		}
	}

	private void setCarWasherName(byte[] headerData) throws IOException {
		int nameLenght = inputStream.readByte();
		
		data = new byte[nameLenght];
		
		inputStream.read(data);
		// FIXME
		// clientModel.setName(Arrays.toString(data));
		clientController.sendMessage(ProtocolProperties.OK_ACK_MSG, headerData);
	}

	public void setProtocolNo(byte[] headerData) throws IOException {
		data = new byte[ProtocolProperties.FOUR_BYTES_MSG];

		inputStream.read(data);
		clientModel.setProtocolNumber(Integer.valueOf(Arrays.toString(data)));

		clientController.sendMessage(ProtocolProperties.OK_ACK_MSG, headerData);
	}

	public void setCarWasherNo(byte[] headerData) throws IOException {
		data = new byte[ProtocolProperties.FOUR_BYTES_MSG];

		inputStream.read(data);
		clientModel.setCarWasherNo(Integer.valueOf(Arrays.toString(data)));
		clientController.sendMessage(ProtocolProperties.OK_ACK_MSG, headerData);
	}

	public void setStatesAmount(byte[] headerData) throws IOException {
		clientModel.setStatesAmmount(Integer.valueOf(inputStream.readByte()));
		clientController.sendMessage(ProtocolProperties.OK_ACK_MSG, headerData);
	}

	public void sendOnePLNCoinsCountRequest(byte[] header) throws IOException {
		data = new byte[ProtocolProperties.TWO_BYTES_MSG];
		inputStream.read(data);

		switch (header[ProtocolProperties.FIRST_MSG_BYTE]) {
		case (byte) 0x00:
			coinValue = "1 z�";
			break;
		case (byte) 0x01:
			coinValue = "2 z�";
			break;
		case (byte) 0x02:
			coinValue = "5 z�";
			break;
		}

		FilesOperations.saveDataToFile(System.currentTimeMillis()
				+ "   Grupa: CYKLICZNE_DANE_FINANSOWE ilo�� monet: " + data[0]
				+ ',' + data[1] + "  " + coinValue + " stanowisko :  "
				+ header[ProtocolProperties.SECOND_MSG_BYTE] + "\n");
		clientModel.setOnePLNCoins(data[0]);

		clientController.sendMessage(ProtocolProperties.OK_ACK_MSG, header);
	}

}
