package com.niit.reto.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.niit.reto.*")
public class DispConfig extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver view(){
		
		InternalResourceViewResolver vr=new InternalResourceViewResolver();
		vr.setViewClass(JstlView.class);
		vr.setPrefix("/WEB-INF/");
		vr.setSuffix(".jsp");
		return vr;
	}
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver reslover(){
		CommonsMultipartResolver res=new CommonsMultipartResolver();
		return res;
	}
	
}
