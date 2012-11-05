package com.onlymega.dgaisan.html5maker.model;

public enum ActiveStatusEnum {
	INACTIVE((short) 0),
	
	ACTIVE((short) 1);
	
	private short val;
	
	private ActiveStatusEnum(short value) {
		this.val = value;
	}
	
	public short getValue() {
		return val;
	}
}
