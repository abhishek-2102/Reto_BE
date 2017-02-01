package com.niit.reto.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.reto.dao.JobAppliedDao;
import com.niit.reto.model.JobAppliedDetails;

@Repository
@Transactional
public class JobAppliedImpl implements JobAppliedDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public JobAppliedImpl(SessionFactory sess){
		this.sessionFactory=sess;
	}

	public JobAppliedDetails applyNewJob(JobAppliedDetails apply) {
		sessionFactory.getCurrentSession().save(apply);
		return apply;
	}

	public JobAppliedDetails alreadyApplied(JobAppliedDetails apply) {
		String validate="from JobAppliedDetails where userid='"+apply.getUserid()+"' and jobid='"+apply.getJobid()+"'";
		Query list=sessionFactory.getCurrentSession().createQuery(validate);
		JobAppliedDetails job=(JobAppliedDetails) list.uniqueResult();
		return job;
	}

	public List<JobAppliedDetails> userApplied(String id) {
		String hql="from JobAppliedDetails where userid='"+id+"'";
		Query list=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<JobAppliedDetails> appliedlist=list.list();
		return appliedlist;
	}

	public List<JobAppliedDetails> getAllJob() {
		String hql="from JobAppliedDetails";
		Query list=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<JobAppliedDetails> appliedlist=list.list();
		return appliedlist;
	}

	public JobAppliedDetails updateStatus(JobAppliedDetails apply) {
		sessionFactory.getCurrentSession().update(apply);
		return apply;
	}
		
}
