package com.innovez.learn.chat.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="chat_user")
@SuppressWarnings("serial")
public class User implements Serializable {
	@Id
	@Column(name="username")
	@NotNull(groups={RegistrationGroup.class})
	private String username;
	
	@NotNull
	@Column(name="password")
	private String password;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="register_date")
	private Date registerDate = new Date();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	public static interface RegistrationGroup {}
}