package com.autospa.models;

import java.io.Serializable;

public class ClientModel implements Serializable{

	private String name;
	private static int iterator = 0;
	private int id = ++iterator;
	private boolean isAvaliable;
	private int onePLNCoins;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static int getIterator() {
		return iterator;
	}
	public static void setIterator(int iterator) {
		ClientModel.iterator = iterator;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAvaliable() {
		return isAvaliable;
	}
	public void setAvaliable(boolean isAvaliable) {
		this.isAvaliable = isAvaliable;
	}
	public int getOnePLNCoins() {
		return onePLNCoins;
	}
	public void setOnePLNCoins(int onePLNCoins) {
		this.onePLNCoins = onePLNCoins;
	}
	
	@Override
	public boolean equals(Object clientModel) {
		ClientModel client = (ClientModel) clientModel;
		return this.getName().equals(client.getName());
	}
}
