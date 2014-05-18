package com.innovez.learn.chat.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.innovez.learn.chat.entity.User;

@Transactional(readOnly=true)
public class JpaUserManagementService implements UserManagementService {
	private Logger logger = Logger.getLogger(JpaUserManagementService.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void registerUser(User user) {
		logger.debug("Register user.");
		user.setPassword("password");
		entityManager.persist(user);
		entityManager.flush();
	}

	@Override
	public boolean isRegisteredUser(String username) {
		Assert.notNull(username, "Username parameter should not be null.");
		return entityManager.createQuery("SELECT COUNT(u.id)>0 FROM User u WHERE u.username = :username", Boolean.class)
				.setParameter("username", username)
				.getSingleResult();
	}

	@Override
	public List<User> getUsers() {
		return entityManager.createQuery("SELECT u FROM User u ORDER BY u.username", User.class)
				.getResultList();
	}

	@Override
	public User getUser(String username) {
		Assert.notNull(username, "Username parameter should not be null.");
		return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
				.setParameter("username", username)
				.getSingleResult();
	}

	@Override
	public void updateUser(User user) {
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUser(String username) {
		Assert.notNull(username, "Username parameter should not be null.");
		entityManager.remove(getUser(username));
		entityManager.flush();
	}
}