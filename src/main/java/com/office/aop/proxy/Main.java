package com.office.aop.proxy;

import com.office.ioc.dao.UserDao;
import com.office.ioc.dao.impl.UserDaoImpl;
import com.office.ioc.entity.User;

public class Main {

	public static void main(String[] args) {
		LogHandel logHandel=new LogHandel();
		UserDao userDao=(UserDao) logHandel.newProxyInstance(new UserDaoImpl());
		userDao.add(new User());
	}
}
