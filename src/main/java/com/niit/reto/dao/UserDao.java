package com.niit.reto.dao;

import java.util.List;

import com.niit.reto.model.UserDetails;

public interface UserDao {
	public boolean saveUser(UserDetails user);
	public List<UserDetails> getAllUsers();
	public UserDetails getUser(String id);
	public boolean upUser(UserDetails user);
	public UserDetails loginUser(UserDetails user);
	
	
	
}
