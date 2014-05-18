package com.innovez.learn.chat.service;

import java.util.List;

import com.innovez.learn.chat.entity.User;

public interface UserManagementService {
	void registerUser(User user);
	boolean isRegisteredUser(String username);
	List<User> getUsers();
	User getUser(String username);
	void updateUser(User user);
	void deleteUser(String username);
}