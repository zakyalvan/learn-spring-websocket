package com.innovez.learn.chat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.innovez.learn.chat.entity.User;
import com.innovez.learn.chat.security.UserDetailsAdapter;

public class SecurityManagerService implements UserDetailsService {
	@Autowired
	private UserManagementService userManagementService;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(!userManagementService.isRegisteredUser(username)) {
			throw new UsernameNotFoundException(String.format("User with username %s not found in user database.", username));
		}
		User user = userManagementService.getUser(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new UserDetailsAdapter(user.getUsername(), user.getPassword(), authorities);
	}
}
