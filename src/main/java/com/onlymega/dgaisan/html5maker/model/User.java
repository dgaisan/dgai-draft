package com.onlymega.dgaisan.html5maker.model;

import java.io.Serializable;
import java.util.Date;

/**
 * User model class.
 * 
 * @author Dmitri Gaisan
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = 37272372931L;
	
	private int userId;
	private String login;
	private String pass;
	private String userName;
	private Date dateCreated;
	private short verified;
	private short active;
	int role;
	
	public User() {}
	
	public User(int userId, String login, String pass, String userName, Date dateCreated) {
		this.userId = userId;
		this.login = login;
		this.pass = pass;
		this.userName = userName;
		this.dateCreated = dateCreated;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public void setVerified(short verified) {
		this.verified = verified;
	}

	public short getVerified() {
		return verified;
	}

	public void setActive(short active) {
		this.active = active;
	}

	public short getActive() {
		return active;
	}

	@Override
	public String toString() {
		return login + ", " + pass + ", " +
			userName + ", " + role;
	}
}
