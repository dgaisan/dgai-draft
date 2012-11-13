package com.onlymega.dgaisan.html5maker.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/**
	 * Method that verifies the validity of the email format:
	 * <name>@<domain>.<extension> .
	 * 
	 * @param aEmailAddress a {@link String} representing an email, can't be {@code null}
	 * @return {@code true} if valid, {@code false} otherwise.
	 */
	public static boolean isValidEmailAddress(String aEmailAddress) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		
		Matcher matcher = pattern.matcher(aEmailAddress);
		
		return matcher.matches();
	}

	
}
