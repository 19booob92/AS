package com.autospa.controllers;

import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
		if (server == null) {
			return Collections.EMPTY_LIST;
		} else {
			return server.getClientsList();
		}
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public @ResponseBody boolean start() {
		
			try {
				if (server == null) {
					server = new ServerController();
					server.startServer(ServerProperties.SERVER_IP);
					return true;
				} else if (server.isRunning() == false) {
					server.setIsRunning(true);
					return true;
				} else {
					logger.error("Serwer jest ju¿ uruchomiony");
					return false;
				}
			} catch (IOException e) {
				logger.error("Nie uda³o siê uruchomiæ serwera");
				e.printStackTrace();
				return false;
			}

	}

	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public @ResponseBody void stop() {
		server.setIsRunning(false);
	}

}
