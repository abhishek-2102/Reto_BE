package com.niit.reto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.reto.dao.AdminDao;
import com.niit.reto.model.UserDetails;

@RestController
public class AdminController {
	
	@Autowired
	AdminDao adao;
	
	@RequestMapping(value="/update/{id}/{status}",method=RequestMethod.GET)
	public ResponseEntity<UserDetails> accept(@PathVariable String id,@PathVariable String status){
		UserDetails user=adao.updateUser(id,status);
		return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/change/{id}/{role}",method=RequestMethod.GET)
	public ResponseEntity<UserDetails> uprole(@PathVariable String id,@PathVariable String role){
		UserDetails user=adao.updateRole(id, role);
		return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
	}
}
