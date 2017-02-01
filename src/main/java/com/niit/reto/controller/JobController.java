package com.niit.reto.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.reto.dao.JobDao;
import com.niit.reto.model.JobDetails;

@RestController
public class JobController {

	@Autowired
	JobDao jdao;
	
	@RequestMapping(value="/savejob",method=RequestMethod.POST)
	public ResponseEntity<JobDetails> saveJob(@RequestBody JobDetails job){
		
		job.setCreatedate(new Date());
		JobDetails jdetails=jdao.saveJob(job);
		jdetails.setCode("400");
		jdetails.setMsg("New job created successfully");
		return new ResponseEntity<JobDetails>(jdetails,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getalljobs",method=RequestMethod.GET)
	public ResponseEntity<List<JobDetails>> getAllJobs(){
		
		List<JobDetails> jlist=jdao.getalljobs();
		return new ResponseEntity<List<JobDetails>>(jlist,HttpStatus.OK);
	}
	
	@RequestMapping(value="/editjob",method=RequestMethod.POST)
	public ResponseEntity<JobDetails> editJob(@RequestBody JobDetails job){
		
		jdao.editjob(job);
		job.setCode("400");
		job.setMsg("Job Updated successfully");
		return new ResponseEntity<JobDetails>(job,HttpStatus.OK);
	}
	
	@RequestMapping(value="/deletejob",method=RequestMethod.POST)
	public ResponseEntity<JobDetails> deleteJob(@RequestBody JobDetails job){
		
		jdao.deletejob(job);
		job.setCode("400");
		job.setMsg("Job Deleted successfully");
		return new ResponseEntity<JobDetails>(job,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getsolojob/{id}",method=RequestMethod.GET)
	public ResponseEntity<JobDetails> getsolojob(@PathVariable String id){
		JobDetails job=jdao.getsolo(id);
		return new ResponseEntity<JobDetails>(job,HttpStatus.OK);
	}
	
}