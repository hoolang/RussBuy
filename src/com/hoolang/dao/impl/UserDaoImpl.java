package com.hoolang.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoolang.dao.base.UserDao;
import com.hoolang.dao.impl.base.BaseDaoImpl;
import com.hoolang.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

}
