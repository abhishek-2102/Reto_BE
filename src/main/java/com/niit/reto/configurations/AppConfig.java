package com.niit.reto.configurations;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.reto.model.BlogDetails;
import com.niit.reto.model.FriendsDetails;
import com.niit.reto.model.JobAppliedDetails;
import com.niit.reto.model.JobDetails;
import com.niit.reto.model.UserDetails;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.niit.reto.*")
public class AppConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource(){
		DriverManagerDataSource dvmd = new DriverManagerDataSource();
		dvmd.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dvmd.setUrl("jdbc:oracle:thin:@//localhost:1521/XE ");
		dvmd.setUsername("RETO_DB");
        dvmd.setPassword("system");
		return dvmd;		
	}
	
	public Properties getProperties(){
		Properties prop = new Properties();
		prop.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		return prop;
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSF(DataSource source){
		
		LocalSessionFactoryBuilder session=new LocalSessionFactoryBuilder(source);
		session.addProperties(getProperties());
		session.addAnnotatedClass(UserDetails.class);
		session.addAnnotatedClass(BlogDetails.class);
		session.addAnnotatedClass(FriendsDetails.class);
		session.addAnnotatedClass(JobDetails.class);
		session.addAnnotatedClass(JobAppliedDetails.class);
		return session.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransaction(SessionFactory s) {
		HibernateTransactionManager htm = new HibernateTransactionManager(s);
		return htm;
	}
}
