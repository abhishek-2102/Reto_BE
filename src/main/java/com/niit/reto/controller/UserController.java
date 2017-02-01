package com.niit.reto.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.reto.dao.UserDao;
import com.niit.reto.model.UserDetails;

@RestController
public class UserController {
	
	@Autowired
	UserDao udao;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<UserDetails> saveUserDetails(@RequestBody UserDetails user){
		
		user.setStatus("NEW");
		udao.saveUser(user);
		user.setCode("200");
		user.setMsg("Registered Succesfully");
		return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	public ResponseEntity<List<UserDetails>> getAllUserDetails(){
		
		List<UserDetails> ulist=udao.getAllUsers();
		return new ResponseEntity<List<UserDetails>>(ulist,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getsolo/{id}")
	public ResponseEntity<UserDetails> getUserDetails(@PathVariable("id") String id){
		
		UserDetails user_solo=udao.getUser(id);
		user_solo.setCode("200");
		user_solo.setMsg("User found with Username:"+id);
		return new ResponseEntity<UserDetails>(user_solo,HttpStatus.OK);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<UserDetails> updateUser(@RequestBody UserDetails user){
		
		udao.upUser(user);
		user.setCode("400");
		user.setMsg("Detials updated succesfully");
		return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<UserDetails> loginUser(@RequestBody UserDetails user,HttpSession session){
		
		UserDetails u=udao.loginUser(user);
		if(u==null){
			UserDetails un= new UserDetails();
			un.setCode("400");
			un.setMsg("Invalid credential");
			return new ResponseEntity<UserDetails>(un,HttpStatus.OK);
		}
		u.setCode("200");
		u.setMsg("Login Successfully");
		session.setAttribute("currentUser", u);
		return new ResponseEntity<UserDetails>(u,HttpStatus.OK);
	}
	
	
	
}
