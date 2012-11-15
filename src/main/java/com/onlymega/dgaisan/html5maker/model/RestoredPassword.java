package com.onlymega.dgaisan.html5maker.model;

import java.util.Date;

public class RestoredPassword {
	private int regId;
	private String activationCode;
	private Date dateCreated;
	private User user;

	
	public RestoredPassword() {}
	
	public RestoredPassword(String activationCode, User user, Date date) {
		this.activationCode = activationCode;
		this.dateCreated = date;
		this.user = user;
	}
	
	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
