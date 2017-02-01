package com.niit.reto.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.reto.dao.JobDao;
import com.niit.reto.model.JobDetails;

@Transactional
@Repository
public class JobImpl implements JobDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public JobImpl(SessionFactory sess){
		this.sessionFactory=sess;
	}
	
	public JobDetails saveJob(JobDetails jobdetails) {
		sessionFactory.getCurrentSession().save(jobdetails);
		return jobdetails;
	}

	public List<JobDetails> getalljobs() {
		String query="from JobDetails";
		Query q=sessionFactory.getCurrentSession().createQuery(query);
		@SuppressWarnings("unchecked")
		List<JobDetails> joblist=q.list();
		return joblist;
	}

	public JobDetails editjob(JobDetails job) {
		sessionFactory.getCurrentSession().update(job);
		return job;
	}

	public JobDetails deletejob(JobDetails job) {
		job.setStatus("Deleted");
		sessionFactory.getCurrentSession().update(job);
		return job;
	}

	public JobDetails getsolo(String id) {
		return (JobDetails) sessionFactory.getCurrentSession().get(JobDetails.class,id);
	}
	
	
}
