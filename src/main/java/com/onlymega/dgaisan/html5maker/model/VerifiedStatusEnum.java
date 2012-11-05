package com.onlymega.dgaisan.html5maker.model;

public enum VerifiedStatusEnum {
	VERIFIED((short) 1),
	NOT_VERIFIED((short) 0);
	
	private short val;
	
	private VerifiedStatusEnum(short value) {
		this.val = value;
	}
	
	public short getValue() {
		return val;
	}
}
