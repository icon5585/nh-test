package com.notable.health.nhtest.data.models;

import com.notable.health.nhtest.utils.IdGenerator;

/**
 * POJO representing a patient
 * 
 * @author Hank DeDona
 *
 */
public class Patient {

	private final int id;

	// There would be a lot more information, addresses, phone numbers, ailments (in
	// a different class), medications, etc.
	private String firstName;

	private String lastName;

	public Patient() {
		id = IdGenerator.getNextNumber();
	}

	public Patient(String firstName, String lastName) {
		this();
		setFirstName(firstName);
		setLastName(lastName);
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		assert (!firstName.isBlank());
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		assert (!lastName.isBlank());
		this.lastName = lastName;
	}
}
