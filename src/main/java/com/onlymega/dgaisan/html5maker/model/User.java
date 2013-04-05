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
	private int verified;
	private int active;
	private int role;
	private int membershipType;
	private String userFolder;
	
	public User() {}
	
	public User(int userId, String login, String pass, String userName, Date dateCreated) {
		this.userId = userId;
		this.login = login;
		this.pass = pass;
		this.dateCreated = dateCreated;
	}
	
	public User(User user) {
		this.userId = user.getUserId();
		this.login = user.getLogin();
		this.pass = user.getPass();
		this.dateCreated = user.getDateCreated();
		this.membershipType = user.getMembershipType();
		this.role = user.getRole();
		this.verified = user.getVerified();
		this.active = user.getActive();
		this.userName = user.getUserName();
		this.userFolder = user.getUserFolder();
		
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
	
	public void setVerified(int verified) {
		this.verified = verified;
	}

	public int getVerified() {
		return verified;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getActive() {
		return active;
	}

	public void setMembershipType(int membershipType) {
		this.membershipType = membershipType;
	}

	public int getMembershipType() {
		return membershipType;
	}

	public String getUserFolder() {
		return userFolder;
	}

	public void setUserFolder(String userFolder) {
		this.userFolder = userFolder;
	}

	@Override
	public String toString() {
		return login + ", " + pass + ", " +
			", " + role + ", " +
			membershipType;
	}
}
