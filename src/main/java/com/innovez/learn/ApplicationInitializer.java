package com.innovez.learn;

import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * Application initializer, this class equivalent to web.xml file.
 * 
 * @author zakyalvan
 */
public class ApplicationInitializer extends AbstractDispatcherServletInitializer {
	@Override
	protected WebApplicationContext createServletApplicationContext() {
		XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
		applicationContext.setConfigLocation("classpath:/META-INF/spring/web/*-appcontext.xml");
		return applicationContext;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
		applicationContext.setConfigLocation("classpath:/META-INF/spring/core/*-appcontext.xml");
		return applicationContext;
	}

	@Override
	protected Filter[] getServletFilters() {
		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
		return new Filter[] {hiddenHttpMethodFilter, openEntityManagerInViewFilter};
	}
}
