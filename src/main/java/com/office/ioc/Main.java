package com.office.ioc;

import com.office.ioc.core.BeanFactory;
import com.office.ioc.core.impl.ClassPathXmlApplicationContext;
import com.office.ioc.entity.User;
import com.office.ioc.service.UserService;

public class Main {

	public static void main(String[] args) throws Exception {
		BeanFactory context = new ClassPathXmlApplicationContext("ioc-beans.xml");
		User student=new User();
		UserService service=(UserService)context.getBean("userService");
		service.add(student);
		
	}

}
