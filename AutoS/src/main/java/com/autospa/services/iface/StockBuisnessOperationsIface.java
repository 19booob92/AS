package com.autospa.services.iface;

import com.autospa.models.UserModel;

public interface StockBuisnessOperationsIface {
	void save(UserModel user);
	void update(UserModel user);
	void delete(UserModel user);
	UserModel findByName(String userName);
	UserModel findBySurName(String userSurName);
	UserModel findById(Long userId);
}
