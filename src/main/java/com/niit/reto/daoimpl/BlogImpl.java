package com.niit.reto.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.reto.dao.BlogDao;
import com.niit.reto.model.BlogDetails;

@Repository
@Transactional
public class BlogImpl implements BlogDao{

	@Autowired
	SessionFactory sessionFactory;
	
	
	public BlogImpl(SessionFactory sess){
		this.sessionFactory=sess;
	}
	
	public BlogDetails saveBlog(BlogDetails blog) {
		sessionFactory.getCurrentSession().save(blog);
		System.out.println(blog);
		return blog;
	}
	
	public List<BlogDetails> getAllBlogs(){
		String hql="from BlogDetails";
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BlogDetails> blist=q.list();
		return blist;
	}

	public BlogDetails getBlog(int id) {
		return 	(BlogDetails) sessionFactory.getCurrentSession().get(BlogDetails.class,id); 
	}
	
	public BlogDetails updateBlog(BlogDetails blog) {
		sessionFactory.getCurrentSession().update(blog);
		return blog;
	}
	
	public BlogDetails delBlog(BlogDetails blog) {
		sessionFactory.getCurrentSession().delete(blog);;
		return blog;
	}

}
