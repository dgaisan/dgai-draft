package com.onlymega.dgaisan.html5maker.model;

/**
 * Enumeration of available membership/subscription types.
 * 
 * @author Dmitri Gaisan
 * @deprecated
 */
public enum MembershipTypesEnum {
	PREMIUM(1), LIMITED(2);
	
	private int value;
	
	private MembershipTypesEnum(int val) {
		value = val;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
