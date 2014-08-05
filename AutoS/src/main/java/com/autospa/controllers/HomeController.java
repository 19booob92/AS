package com.autospa.controllers;

import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.autospa.models.ClientModel;
import com.autospa.properties.ServerProperties;

@Controller
public class HomeController {

	private ServerController server = null;
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(value = "/carWashers", method = RequestMethod.GET)
	public @ResponseBody List<ClientModel> getConnectedCarWashers() {
		return server.getClientsList();
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public @ResponseBody void start() {
		if (server == null) {
			try {
				server = new ServerController();
				server.startServer(ServerProperties.SERVER_IP);
			} catch (IOException e) {
				logger.error("Nie uda�o si� uruchomi� serwera");
				e.printStackTrace();
			}
		} else {
			logger.error("Serwer jest ju� uruchomiony");
		}

	}

	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public @ResponseBody void stop() {
		server.stopServer();
		server = null;
	}

}
