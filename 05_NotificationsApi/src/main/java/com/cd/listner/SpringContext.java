package com.cd.listner;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContext implements ApplicationContextAware {

	private static ApplicationContext context;
	
	public static <T extends Object> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		setContext(applicationContext);

	}

	private static synchronized void setContext(ApplicationContext applicationContext) {
		// TODO Auto-generated method stub
		SpringContext.context=context;
		}

}
