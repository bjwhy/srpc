package com.why.srpc.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RequestMapping {
	private Map<String, Object> classNameBeanMap;
	private Map<String, ActionMethod> mapping = new HashMap<String, ActionMethod>();

	public RequestMapping(Map<String, Object> classNameBeanMap) {
		this.classNameBeanMap = classNameBeanMap;
		initMapping();
	}

	public void initMapping() {
		for (Entry<String, Object> entry : classNameBeanMap.entrySet()) {
			String className = entry.getKey();

			try {
				Class<?> actionClass = Class.forName(className);

				for (Method method : actionClass.getMethods()) {
					Path refs = method.getAnnotation(Path.class);
					if (refs != null) {
						String pathVal = String.valueOf(refs.value());
						if (pathVal.length() <= 0) {
							pathVal = method.getName();
						}

						if (mapping.containsKey(pathVal)) {
							throw new DupliPathException(pathVal + " in the path map is duplicate");
						}

						makeAccessible(method);

						/**
						 * 从spring ioc容器中获取相应的bean
						 */
						Object bean = entry.getValue();
						ActionMethod actionMethod = new ActionMethod(bean, method);

						mapping.put(pathVal, actionMethod);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public ActionMethod getMethod(String uri) {
		return mapping.get(uri);
	}

	private void makeAccessible(Method method) {
		method.setAccessible(true);
	}
}
