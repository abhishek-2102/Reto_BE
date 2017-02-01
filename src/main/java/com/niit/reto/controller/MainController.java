package com.niit.reto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.reto.dao.UserDao;

@Controller
public class MainController {
	
	@Autowired
	UserDao udao;
	
	@RequestMapping("/")
	public String control(){
		
		return "index";
	}
}
