package com.niit.reto.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.reto.dao.UserDao;
import com.niit.reto.model.UserDetails;

@Repository
@Transactional
public class UserImpl implements UserDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public UserImpl(SessionFactory sess){
		this.sessionFactory=sess;
	}
	
	public boolean saveUser(UserDetails user) {
		try{
			sessionFactory.getCurrentSession().save(user);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}//end save user method
		
	public List<UserDetails> getAllUsers() {
		String query="from UserDetails";
		Query orl=sessionFactory.getCurrentSession().createQuery(query);
		@SuppressWarnings("unchecked")
		List<UserDetails> u_list=orl.list();
		return u_list;
	}

	public UserDetails getUser(String id) {
		return 	(UserDetails) sessionFactory.getCurrentSession().get(UserDetails.class,id); 
	}
	
	public boolean upUser(UserDetails user) {
		try{
			sessionFactory.getCurrentSession().update(user);;
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public UserDetails loginUser(UserDetails user) {
		
		String hql="from UserDetails where id='"+user.getId()+"' and password='"+user.getPassword()+"'";
		Query list=sessionFactory.getCurrentSession().createQuery(hql);
		UserDetails u=(UserDetails)list.uniqueResult();
		return u;
	}
	
}
