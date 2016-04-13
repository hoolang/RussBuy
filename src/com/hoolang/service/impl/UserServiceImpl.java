package com.hoolang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoolang.dao.base.UserDao;
import com.hoolang.entity.User;
import com.hoolang.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}
//	@Override
//	public List<User> latestUsers(String HQL, int max) {
//		String HQL = "";
//		return null;
//	}

}
