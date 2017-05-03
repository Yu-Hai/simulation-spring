package com.office.ioc.dao.impl;

import com.office.ioc.dao.UserDao;
import com.office.ioc.entity.User;

public class UserDaoImpl implements UserDao {

	public void add(User user) {
		System.out.println("user is saved !");
	}

}
