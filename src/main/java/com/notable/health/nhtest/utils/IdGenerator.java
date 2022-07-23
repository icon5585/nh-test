package com.notable.health.nhtest.utils;

/**
 * Hack way to generate unique IDs that would normally be handled by a DB
 * 
 * @author Hank DeDona
 *
 */
public final class IdGenerator {

	private static int nextNumber = 0;
	
	public static int getNextNumber() {
		return ++nextNumber;
	}
	
}
