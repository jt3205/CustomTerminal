package com.jnorol.terminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class URIRouter {
	@RequestMapping(value = "/terminal/{id}", method = RequestMethod.GET)
	public String mainPage(@PathVariable String id, Model model) {
		System.out.println("sad");
		return "temp";
	}
}
