package com.niit.reto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.reto.dao.BlogDao;
import com.niit.reto.model.BlogDetails;

@RestController
public class BlogController {
	
	@Autowired
	BlogDao bdao;
	
	@RequestMapping(value="/saveblog",method=RequestMethod.POST)
	public ResponseEntity<BlogDetails> saveblog(@RequestBody BlogDetails blog){
		BlogDetails b=bdao.saveBlog(blog);
		b.setCode("400");
		b.setMsg("Blog successfully created");
		return new ResponseEntity<BlogDetails>(b,HttpStatus.OK);
	}
	
	@RequestMapping(value="/viewblog",method=RequestMethod.GET)
	public ResponseEntity<List<BlogDetails>> getblogs(){
		
		List<BlogDetails> blist=bdao.getAllBlogs();	
		return new ResponseEntity<List<BlogDetails>>(blist,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getblog/{id}",method=RequestMethod.GET)
	public ResponseEntity<BlogDetails> getblog(@PathVariable int id){
		
		BlogDetails blog=bdao.getBlog(id);
		return new ResponseEntity<BlogDetails>(blog,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateblog",method=RequestMethod.POST)
	public ResponseEntity<BlogDetails> updateblog(@RequestBody BlogDetails b){
		BlogDetails bdtls=bdao.updateBlog(b);
		bdtls.setCode("400");
		bdtls.setMsg("Blog updated successfully");
		return new ResponseEntity<BlogDetails>(bdtls,HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteblog",method=RequestMethod.POST)
	public ResponseEntity<BlogDetails> deleteblog(@RequestBody BlogDetails b){
		BlogDetails bdtls=bdao.delBlog(b);
		bdtls.setCode("400");
		bdtls.setMsg("Blog deleted successfully");
		return new ResponseEntity<BlogDetails>(bdtls,HttpStatus.OK);
	}
}
