package com.autospa.services.impl;

import org.dom4j.util.UserDataAttribute;

import com.autospa.dao.iface.UserModelDaoIface;
import com.autospa.models.UserModel;
import com.autospa.services.iface.StockBuisnessOperationsIface;

public class StockBuisnessOperationsImpl implements StockBuisnessOperationsIface{

	private UserModelDaoIface userModelDao;

	public UserModelDaoIface getUserModelDao() {
		return userModelDao;
	}

	public void setUserModelDao(UserModelDaoIface userModelDao) {
		this.userModelDao = userModelDao;
	}

	@Override
	public void save(UserModel user) {
		userModelDao.save(user);
	}

	@Override
	public void update(UserModel user) {
		userModelDao.update(user);
	}

	@Override
	public void delete(UserModel user) {
		userModelDao.delete(user);
	}

	@Override
	public UserModel findByName(String userName) {
		return userModelDao.findByName(userName);
	}

	@Override
	public UserModel findBySurName(String userSurName) {
		return userModelDao.findBySurName(userSurName);
	}

	@Override
	public UserModel findById(Long userId) {
		return userModelDao.findById(userId);
	}

}
