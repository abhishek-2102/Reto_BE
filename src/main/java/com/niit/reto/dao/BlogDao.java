package com.niit.reto.dao;

import java.util.List;

import com.niit.reto.model.BlogDetails;

public interface BlogDao {
	
	public BlogDetails saveBlog(BlogDetails blog);
	public List<BlogDetails> getAllBlogs();
	public BlogDetails getBlog(int id);
	public BlogDetails updateBlog(BlogDetails blog);
	public BlogDetails delBlog(BlogDetails blog);
	
}
