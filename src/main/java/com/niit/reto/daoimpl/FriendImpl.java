package com.niit.reto.daoimpl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.reto.dao.FriendDao;
import com.niit.reto.model.FriendsDetails;

@Repository
@Transactional
public class FriendImpl implements FriendDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public FriendImpl(SessionFactory sess){
		this.sessionFactory=sess;
	}
	
	public FriendsDetails sendrequest(FriendsDetails pair) {
		sessionFactory.getCurrentSession().save(pair);
		return pair;
	}
	
	//for friend objects
	public ArrayList<FriendsDetails> myFriends(String userid) {
		
			String qu1="from FriendsDetails where userid='"+userid+"'";
			String qu2="from FriendsDetails where friendid='"+userid+"'";
			Query query1=sessionFactory.getCurrentSession().createQuery(qu1);
			Query query2=sessionFactory.getCurrentSession().createQuery(qu2);
			@SuppressWarnings("unchecked")
			ArrayList<FriendsDetails> list1 = (ArrayList<FriendsDetails>) query1.list();
			@SuppressWarnings("unchecked")
			ArrayList<FriendsDetails> list2=(ArrayList<FriendsDetails>) query2.list();
			list1.addAll(list2);
			return list1;
	}
	
	//for string friends
	public ArrayList<String> myFriendsString(String userid) {
		
		String qu1="select friendid from FriendsDetails where userid='"+userid+"'";
		String qu2="select userid from FriendsDetails where friendid='"+userid+"'";
		Query query1=sessionFactory.getCurrentSession().createQuery(qu1);
		Query query2=sessionFactory.getCurrentSession().createQuery(qu2);
		@SuppressWarnings("unchecked")
		ArrayList<String> list1 = (ArrayList<String>) query1.list();
		@SuppressWarnings("unchecked")
		ArrayList<String> list2=(ArrayList<String>) query2.list();
		list1.addAll(list2);
		return list1;
	}

	public ArrayList<String> getnew(String id) {
		
		String allq="select id from UserDetails";
		Query query=sessionFactory.getCurrentSession().createQuery(allq);
		@SuppressWarnings("unchecked")
		ArrayList<String> all = (ArrayList<String>) query.list();
		ArrayList<String> friendlist =myFriendsString(id);
		friendlist.add(id);
		all.removeAll(friendlist);
		return all;
	}
	
	public ArrayList<String> myFriendRequest(String userid) {
		
		String hql="select userid from FriendsDetails where friendid='"+userid+"' and status='New'";
		Query que=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<String> list=(ArrayList<String>) que.list();
		return list;
	}

	public ArrayList<String> mySentRequest(String userid) {
		
		String hql="select friendid from FriendsDetails where userid='"+userid+"' and status='New'";
		Query que=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<String> list=(ArrayList<String>) que.list();
		return list;
	}

	public boolean changeStatus(FriendsDetails pair) {
		
		try {
			sessionFactory.getCurrentSession().update(pair);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public FriendsDetails cancelRequest(FriendsDetails pair) {
		sessionFactory.getCurrentSession().delete(pair);
		return pair;
	}
	
}
