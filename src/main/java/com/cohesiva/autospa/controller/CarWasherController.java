package com.cohesiva.autospa.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cohesiva.autospa.model.CarWasher;
import com.cohesiva.autospa.properties.ServerProperties;

@Controller
public class CarWasherController {

	@Autowired
	private ServerController server;

	@RequestMapping(value = "/carWashers", method = RequestMethod.GET)
	public @ResponseBody List<CarWasher> getConnectedCarWashers() {

		if (!server.isRunning()) {
			return Collections.EMPTY_LIST;
		} else {
			return server.getClientsList();
		}
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public @ResponseBody boolean start() {

		try {
			if (!server.isRunning()) {
				server.startServer(ServerProperties.SERVER_IP);
				server.setIsRunning(true);
				return true;
			} else {
				System.err.println("Serwer jest juz uruchomiony");
				return false;
			}
		} catch (IOException e) {
			System.err.println("Nie udalo sie uruchomi� serwera");
			e.printStackTrace();
			return false;
		}

	}

	@RequestMapping(value = "/serverStatus", method = RequestMethod.GET)
	public @ResponseBody boolean getSerwerStatus() {
		if (server != null && server.isRunning()) {
			return true;
		} else {
			return false;
		}

	}

	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public @ResponseBody void stop() {
		server.setIsRunning(false);
	}
}
