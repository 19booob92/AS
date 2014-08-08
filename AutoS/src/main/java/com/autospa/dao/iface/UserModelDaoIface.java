package com.autospa.dao.iface;

import com.autospa.models.UserModel;

public interface UserModelDaoIface {

	public void save(UserModel user);
	public void update(UserModel user);
	public void delete(UserModel user);
	public UserModel findByName(String userName);
	public UserModel findBySurName(String userSurName);
	public UserModel findById(Long userId);

}
