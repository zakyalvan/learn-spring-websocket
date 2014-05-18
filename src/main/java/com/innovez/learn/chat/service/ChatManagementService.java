package com.innovez.learn.chat.service;

public interface ChatManagementService {
	/**
	 * Start new user's chat session.
	 * 
	 * @param username
	 * @param session
	 */
	void startSession(String username, String password);
	
	/**
	 * End user's chat session.
	 * 
	 * @param username
	 */
	void endSession(String username);
}