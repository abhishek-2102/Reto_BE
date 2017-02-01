package com.niit.reto.dao;

import java.util.List;

import com.niit.reto.model.JobAppliedDetails;

public interface JobAppliedDao {
	
	public JobAppliedDetails applyNewJob(JobAppliedDetails apply);
	public JobAppliedDetails alreadyApplied(JobAppliedDetails apply);
	public List<JobAppliedDetails> userApplied(String id);
	public List<JobAppliedDetails> getAllJob();
	public JobAppliedDetails updateStatus(JobAppliedDetails apply);
}
