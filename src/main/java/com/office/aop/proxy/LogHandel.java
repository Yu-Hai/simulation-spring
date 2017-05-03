package com.office.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandel implements InvocationHandler{
	
	/** 要处理的对象(也就是我们要在方法前后加上业务逻辑的对象) **/
	private Object targetObject;
	
	public Object newProxyInstance(Object targetObject){
		this.targetObject=targetObject;
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("will excute method :" + method.getName());
		System.out.print("parameters：");
		for (Object object : args) {
			System.out.print(object+"	");
		}
		System.out.println();
		Object result=null;
		//执行真正的方法之前的日志记录
		System.out.println(method.getName()+" Method Start!");
		//执行真正的方法（反射机制）
		result=method.invoke(targetObject, args);
		//执行真正的方法之后的日志记录
		System.out.println(method.getName()+" Method End!");
		return result;
	}

}
