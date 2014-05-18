package com.innovez.learn;

import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import com.innovez.learn.basic.BasicWebSocketConfig;
import com.innovez.learn.chat.ChatWebSocketConfig;
import com.innovez.learn.greet.GreetWebSocketConfig;

public class WebappInitializer extends AbstractDispatcherServletInitializer {
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
		return new Filter[] {new OpenEntityManagerInViewFilter()};
	}
}
