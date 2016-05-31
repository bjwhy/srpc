package com.why.srpc.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class ServerContext implements ApplicationContextAware {
	private RequestMapping mapping;

	public RequestMapping getMapping() {
		return mapping;
	}

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(Controller.class);
		Map<String, Object> classNameBeanMap = new HashMap<String, Object>();

		for (Object serviceBean : serviceBeanMap.values()) {
			String className = serviceBean.getClass().getName();
			classNameBeanMap.put(className, serviceBean);
		}

		RequestMapping mapping = new RequestMapping(classNameBeanMap);
		this.mapping = mapping;
	}
}
