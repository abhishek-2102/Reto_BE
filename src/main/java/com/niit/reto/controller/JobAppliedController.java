package com.niit.reto.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.reto.dao.JobAppliedDao;
import com.niit.reto.model.JobAppliedDetails;
import com.niit.reto.model.UserDetails;

@RestController
public class JobAppliedController {

	@Autowired
	JobAppliedDao jadao;
	
	@RequestMapping(value="/jobapplied/{jobid}",method=RequestMethod.POST)
	public ResponseEntity<JobAppliedDetails> newjob(@PathVariable String jobid,HttpSession sess){
		
		JobAppliedDetails apply=new JobAppliedDetails();
		
		String id=UUID.randomUUID().toString();
		UserDetails user=(UserDetails)sess.getAttribute("currentUser");
		
		apply.setId(id);
		apply.setJobid(jobid);
		apply.setUserid(user.getId());
		apply.setDt(new Date());
		apply.setStatus("New Application");
		
		if(user.getRole().equals("Admin")){
			apply.setCode("200");
			apply.setMsg("Admin can not apply for this job");
			return new ResponseEntity<JobAppliedDetails>(apply,HttpStatus.OK);
		}else{
			if(jadao.alreadyApplied(apply)!=null){
				apply.setCode("200");
				apply.setMsg("You have already applied for this job");
				return new ResponseEntity<JobAppliedDetails>(apply,HttpStatus.OK);
			}else{
				jadao.applyNewJob(apply);
				apply.setCode("400");
				apply.setMsg("Job Applied successfully. Please wait for the response from the admin");
				return new ResponseEntity<JobAppliedDetails>(apply,HttpStatus.OK);
			}
		}
	}//end job applied 
	
	@RequestMapping(value="/myjob",method=RequestMethod.GET)
	public ResponseEntity<List<JobAppliedDetails>> userjob(HttpSession sess){
		UserDetails user=(UserDetails)sess.getAttribute("currentUser");
		List<JobAppliedDetails> myjob=jadao.userApplied(user.getId());
		return new ResponseEntity<List<JobAppliedDetails>>(myjob,HttpStatus.OK);
	}
	
	@RequestMapping(value="/alljobs",method=RequestMethod.GET)
	public ResponseEntity<List<JobAppliedDetails>> alljobs(){
		List<JobAppliedDetails> alljobs=jadao.getAllJob();
		return new ResponseEntity<List<JobAppliedDetails>>(alljobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updatestatus",method=RequestMethod.POST)
	public ResponseEntity<JobAppliedDetails> updateStatus(@RequestBody JobAppliedDetails job){
		JobAppliedDetails apply=jadao.updateStatus(job);
		apply.setCode("400");
		apply.setMsg("Status chanded");
		return new ResponseEntity<JobAppliedDetails>(apply,HttpStatus.OK);
	}
}