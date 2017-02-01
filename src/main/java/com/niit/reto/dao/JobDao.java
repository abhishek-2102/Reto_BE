package com.niit.reto.dao;

import java.util.List;

import com.niit.reto.model.JobDetails;

public interface JobDao {
	
	public JobDetails saveJob(JobDetails jobdetails);
	public List<JobDetails> getalljobs();
	public JobDetails editjob(JobDetails job);
	public JobDetails deletejob(JobDetails job);
	public JobDetails getsolo(String id);
}
