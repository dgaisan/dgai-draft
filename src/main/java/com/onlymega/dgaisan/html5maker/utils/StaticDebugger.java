package com.onlymega.dgaisan.html5maker.utils;

/**
 * Static debugger class.
 * 
 * @author Dmitri Gaisan
 *
 */
public class StaticDebugger {
	
	/**
	 * Prints out to console the details of an exception. 
	 * 
	 * @param exception {@link Throwable}
	 */
	public static void consoleLog(Throwable exception) {
		for (StackTraceElement elem: exception.getStackTrace()) {
			System.out.println(elem.toString());
		}
	}
}
