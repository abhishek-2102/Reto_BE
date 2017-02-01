package com.niit.reto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Table(name="blog_details")
@Entity
@SequenceGenerator(name = "autoinc",sequenceName="autoinc",allocationSize=1)
@Component
public class BlogDetails extends Details{
	
	@Id
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator="autoinc"
			)
	private int id;
	
	@Column
	private String userid;
	
	@Column
	private String title;
	
	@Column
	private Date dt;
	
	@Column
	private String details;
	
	@Column
	private String description;

	@Column
	private int likes;
	
	@Column
	private int dislikes;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
}