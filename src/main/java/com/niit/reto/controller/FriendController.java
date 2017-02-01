package com.niit.reto.controller;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.reto.dao.FriendDao;
import com.niit.reto.model.FriendsDetails;
import com.niit.reto.model.UserDetails;

@RestController
public class FriendController {
	
	@Autowired
	FriendDao fdao;
	
	@RequestMapping(value="/sendrequest",method=RequestMethod.POST)
	public ResponseEntity<FriendsDetails> savefriends(@RequestBody String friendid,HttpSession sess){
		
		String id=UUID.randomUUID().toString();
		FriendsDetails pair=new FriendsDetails();
		pair.setId(id);
		pair.setStatus("New");
		UserDetails user=(UserDetails) sess.getAttribute("currentUser");
		pair.setFriendid(friendid);
		pair.setUserid(user.getId());
		fdao.sendrequest(pair);
		pair.setCode("400");
		pair.setMsg("Friend Request Sent to:"+pair.getFriendid());
		return new ResponseEntity<FriendsDetails>(pair,HttpStatus.OK);
	}
	
	@RequestMapping(value="/myfriends",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<FriendsDetails>> getMyFriends(HttpSession sess){
		UserDetails user=(UserDetails) sess.getAttribute("currentUser");
		ArrayList<FriendsDetails> myfriends=fdao.myFriends(user.getId());
		return new ResponseEntity<ArrayList<FriendsDetails>>(myfriends,HttpStatus.OK);
	}
	
	@RequestMapping(value="/notfriends",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<String>> getNewUsers(HttpSession sess){
		UserDetails user=(UserDetails) sess.getAttribute("currentUser");
		ArrayList<String> newusers= new ArrayList<String>();
		newusers=fdao.getnew(user.getId());
		return new ResponseEntity<ArrayList<String>>(newusers,HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptfriend",method=RequestMethod.POST)
	public ResponseEntity<FriendsDetails> changeStatus(@RequestBody FriendsDetails pair){
		pair.setStatus("Accepted");
		fdao.changeStatus(pair);
		pair.setCode("400");
		pair.setMsg("Friend Request accepted with :"+pair.getFriendid());
		return new ResponseEntity<FriendsDetails>(pair,HttpStatus.OK);
	}
	
	@RequestMapping(value="/cancelrequest",method=RequestMethod.POST)
	public ResponseEntity<FriendsDetails> cancelrequest(@RequestBody FriendsDetails pair){
		fdao.cancelRequest(pair);
		pair.setCode("400");
		pair.setMsg("Friend Request cancelled with friend id:"+pair.getFriendid());
		return new ResponseEntity<FriendsDetails>(pair,HttpStatus.OK);
	}
}
