package com.niit.reto.dao;

import java.util.ArrayList;

import com.niit.reto.model.FriendsDetails;

public interface FriendDao {

	public FriendsDetails sendrequest(FriendsDetails pair);
	public ArrayList<FriendsDetails> myFriends(String userid);
	public ArrayList<String> getnew(String id);
	public boolean changeStatus(FriendsDetails pair);
	public FriendsDetails cancelRequest(FriendsDetails pair);
}
