package com.onlymega.dgaisan.html5maker.utils;

/**
 * Utility class for generating random keys.
 * 
 * @author Dmitri Gaisan
 *
 */
public class KeyGenerator {
	public final static int AVG_KEY_SIZE = 13;
	public final static int AVG_NAME_SIZE = 14;
	public final static int MAX_DIFF_SIZE = 3;
	
	private static int sizeDiff = (int)(Math.random() * MAX_DIFF_SIZE);
	private final static String[] alphabet = new String[]
	          {"a", "b", "c", "d", "e", "f", "g", "h", "k", "l", "m", "n", "i", "x", "z", "k", "w", "p", "o", "q", "r", "s", "t"};
	private final static String[] alphaBlur = new String[]
	          {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	/**
	 * method for generating a random key (used for user authentication).
	 * 
	 * @return key newly generated key.
	 */
	public static String generateKey() {
		String ret = "";
		
		for (int i = 0; i < AVG_KEY_SIZE + sizeDiff; i++) {
			int table = (int)(Math.random() * 2);
			if (table == 0) {
				ret += alphabet[ (int)(Math.random() * alphabet.length)];
			} else {
				ret += alphaBlur[ (int)(Math.random() * alphaBlur.length)];
			}
		}
		return ret;
	}
	
	/**
	 * Method for generating a name hash.
	 * 
	 * @return (13-16) character hash name.
	 */
	public static String generateNameHash() {
		return generateData(AVG_NAME_SIZE + sizeDiff);
	}
	
	public static String generateRegistrationConfirmationCode() {
		return generateData(31);
	}
	
	/**
	 * Method for generating a name hash.
	 * 
	 * @return (13-16) character hash name.
	 */
	private static String generateData(int length) {
		String ret = "";
		
		for (int i = 0; i < length; i++) {
			int table = (int)(Math.random() * 3);
			switch (table) {
			case 0:
				ret += alphabet[ (int)(Math.random() * alphabet.length)];
				break;
			case 1:
				ret += alphaBlur[ (int)(Math.random() * alphaBlur.length)];
				break;
			default:
				ret += alphabet[ (int)(Math.random() * alphabet.length)].toUpperCase();
			}
		}
		return ret;
	}
}
