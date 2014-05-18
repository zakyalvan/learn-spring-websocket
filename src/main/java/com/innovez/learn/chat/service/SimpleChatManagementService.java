package com.innovez.learn.chat.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;

import com.innovez.learn.chat.ChatApplicationException;

public class SimpleChatManagementService implements ChatManagementService {
	private Logger logger = Logger.getLogger(SimpleChatManagementService.class);
	
	@Autowired
	@Qualifier("chatAuthenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Override
	public void startSession(String username, String password) {
		logger.debug("Start new user's chat session.");
		
		try {
			Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
			Authentication authenticationResult = authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(authenticationResult);
			sessionRegistry.registerNewSession(new Long(new Date().getTime()).toString(), authenticationResult.getPrincipal());
			
			logger.debug(sessionRegistry.getAllPrincipals());
		}
		catch(AuthenticationException authException) {
			logger.error(String.format("Problem occured while starting user's chat session : %s", authException.getMessage()));
			throw new ChatApplicationException("Start chat session failed, problem occured on authentication process", authException);
		}
	}

	@Override
	public void endSession(String username) {
		logger.debug("End user's chat session.");
	}
}