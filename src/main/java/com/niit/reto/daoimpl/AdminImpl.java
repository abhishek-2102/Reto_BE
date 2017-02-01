package com.niit.reto.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.reto.dao.AdminDao;
import com.niit.reto.dao.UserDao;
import com.niit.reto.model.UserDetails;

@Repository
@Transactional
public class AdminImpl implements AdminDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserDao udao;
	
	public AdminImpl(SessionFactory sess){
		this.sessionFactory=sess;
	}
	
	public UserDetails updateUser(String id,String status) {
		UserDetails u=udao.getUser(id);
		u.setStatus(status);
		udao.upUser(u);
		return u;
	}
	
	public UserDetails updateRole(String id,String role){
		UserDetails u=udao.getUser(id);
		u.setRole(role);
		udao.upUser(u);
		return u;
	}
}
