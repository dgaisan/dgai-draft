package com.onlymega.dgaisan.html5maker.model;

import java.util.Date;

public class RegistrationConfirmation {
	private int regId;
	private String confirmationCode;
	private Date dateCreated;

	private User user;
	
	public RegistrationConfirmation() {}
	
	public RegistrationConfirmation(String confirmationCode, User user, Date date) {
		this.confirmationCode = confirmationCode;
		this.dateCreated = date;
		this.user = user;
	}
	
	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}
	
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	
}
