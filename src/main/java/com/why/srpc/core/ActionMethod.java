package com.why.srpc.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ActionMethod {

	private Object target;

	private Method method;

	/**
	 * @param target
	 * @param method
	 */
	public ActionMethod(Object target, Method method) {
		this.target = target;
		this.method = method;
	}

	public Object call(Object... args) throws InvocationTargetException, IllegalAccessException {
		return method.invoke(target, args);
	}

	public Object getTarget() {
		return target;
	}

	public Method getMethod() {
		return method;
	}
}
