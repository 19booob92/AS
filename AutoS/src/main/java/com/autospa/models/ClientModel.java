package com.autospa.models;

import java.io.Serializable;
import java.util.Date;

public class ClientModel implements Serializable{

	private static int iterator = 0;
	private int id = ++iterator;
	private String carWasherName;
	private String lastActivity;
	
	private int onePLNCoins;
	private int twoPLNCoins;
	private int fivePLNCoins;
	private int tokensAmount;
	
	private int protocolNumber;
	private int carWasherNo;
	private int statesAmmount;
	
	private boolean isAvaliable;
	
	private int activeZawTechAmount;
	private int pumpsAmount;
	private int lightsAmount;
	
	public String getName() {
		return carWasherName;
	}
	public void setName(String name) {
		this.carWasherName = name;
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
	public String getCarWasherName() {
		return carWasherName;
	}
	public void setCarWasherName(String carWasherName) {
		this.carWasherName = carWasherName;
	}
	public int getTwoPLNCoins() {
		return twoPLNCoins;
	}
	public void setTwoPLNCoins(int twoPLNCoins) {
		this.twoPLNCoins = twoPLNCoins;
	}
	public int getFivePLNCoins() {
		return fivePLNCoins;
	}
	public void setFivePLNCoins(int fivePLNCoins) {
		this.fivePLNCoins = fivePLNCoins;
	}
	public int getTokensAmount() {
		return tokensAmount;
	}
	public void setTokensAmount(int tokensAmount) {
		this.tokensAmount = tokensAmount;
	}
	public int getProtocolNumber() {
		return protocolNumber;
	}
	public void setProtocolNumber(int protocolNumber) {
		this.protocolNumber = protocolNumber;
	}
	public int getCarWasherNo() {
		return carWasherNo;
	}
	public void setCarWasherNo(int carWasherNo) {
		this.carWasherNo = carWasherNo;
	}
	public int getStatesAmmount() {
		return statesAmmount;
	}
	public void setStatesAmmount(int statesAmmount) {
		this.statesAmmount = statesAmmount;
	}
	public int getActiveZawTechAmount() {
		return activeZawTechAmount;
	}
	public void setActiveZawTechAmount(int activeZawTechAmount) {
		this.activeZawTechAmount = activeZawTechAmount;
	}
	public int getPumpsAmount() {
		return pumpsAmount;
	}
	public void setPumpsAmount(int pumpsAmount) {
		this.pumpsAmount = pumpsAmount;
	}
	public int getLightsAmount() {
		return lightsAmount;
	}
	public void setLightsAmount(int lightsAmount) {
		this.lightsAmount = lightsAmount;
	}
	public String getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(String date) {
		this.lastActivity = date;
	}
}
