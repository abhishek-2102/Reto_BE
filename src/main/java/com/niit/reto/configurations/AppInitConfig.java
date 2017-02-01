package com.niit.reto.configurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class[] getRootConfigClasses() {
		
		return new Class[]{AppConfig.class,WebConfig.class};
	}

	@Override
	protected Class[] getServletConfigClasses() {
		
		return new Class[]{AppConfig.class,WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[]{"/"};
	}
}
