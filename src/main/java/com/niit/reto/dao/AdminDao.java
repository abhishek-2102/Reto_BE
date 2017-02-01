package com.niit.reto.dao;


import com.niit.reto.model.UserDetails;

public interface AdminDao {
	public UserDetails updateUser(String id,String status);
	public UserDetails updateRole(String id,String role);
}
