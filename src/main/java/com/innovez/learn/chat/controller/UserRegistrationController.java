package com.innovez.learn.chat.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovez.learn.chat.entity.User;
import com.innovez.learn.chat.entity.User.RegistrationGroup;
import com.innovez.learn.chat.service.ChatManagementService;
import com.innovez.learn.chat.service.UserManagementService;

@Controller
@RequestMapping(value="/chat/users")
public class UserRegistrationController {
	private Logger logger = Logger.getLogger(UserRegistrationController.class);
	
	@Autowired
	private UserManagementService userManagementService;
	
	@Autowired
	private ChatManagementService chatManagementService;
	
	/**
	 * Handle user registration.
	 * 
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value={"", "/"}, method=RequestMethod.POST)
	public @ResponseBody RegistrationResponse register(@Validated(RegistrationGroup.class) @RequestBody User user, BindingResult bindingResult) {
		logger.debug("Handle user registration for user : " + user.getUsername());
		if(user.getUsername() != null && userManagementService.isRegisteredUser(user.getUsername())) {
			logger.error("Given username already registered, reject the value.");
			bindingResult.rejectValue("username", "UserRegistrationController.usernameAlreadyRegistered", "Given username already registered");
		}
		if(bindingResult.hasErrors()) {
			logger.error("Submitted value contains error, reject them.");
			return RegistrationResponse.createFailed(String.format("Registration of user with username %s failed.", user.getUsername()));
		}
		
		userManagementService.registerUser(user);
		chatManagementService.startSession(user.getUsername(), user.getPassword());
		return RegistrationResponse.createSuccess(String.format("Registration of user %s success!", user.getUsername()));
	}
}