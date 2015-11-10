package com.dingdang.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dingdang.user.dao.UserDao;

@Component("userService")
public class UserService
{
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao()
	{
		return userDao;
	}

	public void setUserDao(UserDao userDao)
	{
		this.userDao = userDao;
	}

	/**
	 * login
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 */
	public int checkLogin(String tele, String password)
	{
		return userDao.checkUser(tele, password) > 0 ? 1 : 0;
	}
	
	/**
	 * register
	 * 
	 * @param tele
	 * @param password
	 * @return
	 * 
	 */
	public int saveUser(String tele, String password)
	{
		return userDao.saveUser(tele, password);
	}
}
