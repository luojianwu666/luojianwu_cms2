package com.luojianwu.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luojianwu.cms.common.CmsMd5Util;
import com.luojianwu.cms.dao.UserDao;
import com.luojianwu.cms.pojo.User;
import com.luojianwu.cms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public boolean register(User user) {
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setPassword(CmsMd5Util.string2MD5(user.getPassword()));
		user.setLocked(0);
		user.setScore("0");
		user.setRole("0");
		return userDao.insert(user)>0;
	}

	@Override
	public User getByUsername(String username) {
		return userDao.selectByUsername(username);
	}

	@Override
	public boolean locked(Integer userId) {
		return userDao.updateLocked(userId,1)>0;
	}

	@Override
	public boolean unLocked(Integer userId) {
		return userDao.updateLocked(userId,0)>0;
	}

	@Override
	public int addScore(Integer userId, int score) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<User> getPageInfo(User user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> userList = userDao.select(user);
		return new PageInfo<>(userList);
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return userDao.update(user)>0;
	}

	@Override
	public boolean isExist(String username) {
		// TODO Auto-generated method stub
		return getByUsername(username)!=null;
	}

	@Override
	public User getById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
