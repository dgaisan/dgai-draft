package com.onlymega.dgaisan.html5maker.utils;

import java.security.MessageDigest;

/**
 * Utility class for converting a value into MD5 hash.
 * 
 * @author Dmitri Gaisan
 *
 */
public class MD5Util {
	public static String convertIntoMD5(final String value) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		StringBuffer ret = new StringBuffer();
		byte[] byteData = null;
		
		md.update(value.getBytes());
		byteData = md.digest();
		
        for (int i = 0; i < byteData.length; i++) {
        	ret.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		
		return ret.toString();
	}
}
