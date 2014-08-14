package com.cohesiva.autospa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cohesiva.autospa.model.Slot;
import com.cohesiva.autospa.service.SlotService;

@Controller
@RequestMapping(value = "/slot")
public class SlotController {
	
	@Autowired
	private SlotService slotService;
	
	@RequestMapping(value="/list", produces = "application/json")
	public @ResponseBody List<Slot> listOfusers() {
		return slotService.getSlots();
	}
}
