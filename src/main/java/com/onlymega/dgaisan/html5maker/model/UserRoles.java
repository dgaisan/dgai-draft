package com.onlymega.dgaisan.html5maker.model;

public enum UserRoles {
	/**
	 * Manages system configurations
	 */
	ADMIN(0), 
	
	/**
	 * Premium member, with paid subscription
	 */
	PREMIUM_MEMBER(1), 
	
	/**
	 * Member with a free/limited subscription
	 */
	LIMITED_MEMBER(2);
	
	private int value;
	
	private UserRoles(int val) {
		value = val;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
