package com.onlymega.dgaisan.html5maker.model;

public enum UserRoles {
	/**
	 * Manages system configurations
	 */
	ADMIN(0), 
	
	/**
	 * Member with a free/limited subscription
	 */
	LIMITED_MEMBER(1),
	
	
	/**
	 * Premium member, with paid subscription
	 */
	PREMIUM_MEMBER(2),
	
	/**
	 * Extended Premium member, with paid subscription
	 */
	EXTENDED_PREMIUM_MEMBER(3); 
	
	
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
