package com.autospa.server;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.autospa.main.Client;
import com.autospa.main.Server;
import com.autospa.utils.ServerProperties;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private Server server = null;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/carWashers")
	public @ResponseBody List getConnectedCarWashers() {
		
		return server.getClientsList();
	}
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public @ResponseBody void start() {
        try {
        	server = new Server();
        	server.startServer(ServerProperties.SERVER_IP);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
