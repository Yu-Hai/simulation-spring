package com.office.ioc.service.impl;

import com.office.ioc.dao.UserDao;
import com.office.ioc.entity.User;
import com.office.ioc.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void add(User user) {
		userDao.add(user);
	}

}
