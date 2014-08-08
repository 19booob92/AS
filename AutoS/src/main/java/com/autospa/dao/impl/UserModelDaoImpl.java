package com.autospa.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.autospa.dao.iface.UserModelDaoIface;
import com.autospa.models.UserModel;

public class UserModelDaoImpl extends HibernateDaoSupport implements UserModelDaoIface {

	@Override
	public void save(UserModel user) {
		getHibernateTemplate().save(user);
	}

	@Override
	public void update(UserModel user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public void delete(UserModel user) {
		getHibernateTemplate().delete(user);
	}

	@Override
	public UserModel findByName(String userName) {
		List<UserModel> list = (List<UserModel>) getHibernateTemplate().find("FROM user WHERE imie=?", userName);
		return list.get(0);
	}

	@Override
	public UserModel findBySurName(String userSurName) {
		List<UserModel> list = (List<UserModel>) getHibernateTemplate().find("FROM user WHERE nazwisko=?", userSurName);
		return list.get(0);
	}

	@Override
	public UserModel findById(Long userId) {
		List<UserModel> list = (List<UserModel>) getHibernateTemplate().find("FROM user WHERE id=?", userId);
		return list.get(0);
	}

}
