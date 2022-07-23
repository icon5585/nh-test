package com.notable.health.nhtest.data.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.notable.health.nhtest.utils.IdGenerator;
import com.notable.health.nhtest.validators.AppointmentValidators;

/**
 * POJO representing a doctor
 * 
 * @author Hank DeDona
 *
 */
public class Doctor {

	// There are certainly a lot more fields about a doctor I could add, decided
	// leave them out due to time constraints

	// Might make sense to abstract some of this out (id, name, etc) so we can
	// re-use it for nurses and other healthcare professionals
	private final int id;

	private String firstName;

	private String lastName;

	private List<Appointment> appointments;

	public Doctor() {
		id = IdGenerator.getNextNumber();
		appointments = new ArrayList<Appointment>();
	}

	public Doctor(String firstName, String lastName) {
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

	public List<Appointment> getAppointments() {
		return Collections.unmodifiableList(appointments);
	}

	public void addAppointment(Appointment appointment) {
		assert (appointment != null);

		if (!AppointmentValidators.validateAppointmentIsNot3rdAppointment(this, appointment)) {
			// Thought: Might be better to just throw the exception in the validator, no?
			throw new IllegalArgumentException("Cannot have more than 3 appointments with the same start date/time");
		}

		appointments.add(appointment);
	}

	public boolean deleteAppointmentById(Integer id) {
		assert (id != null);

		int apptIndexToDelete = getAppointmentIndexById(id);

		if (apptIndexToDelete == -1) {
			// Did not find an appointment matching the ID!
			return false;
		}

		return appointments.remove(apptIndexToDelete) != null;
	}

	// TODO: Move this out of the doctor class!!!

	/**
	 * Get an {@link Appointment} provided an appointment ID
	 * 
	 * @param id the {@link Appointment} ID to look for
	 * 
	 * @return the {@link Appointment} if found, <code>null</code> otherwise
	 */
	public Appointment getAppointmentById(Integer id) {
		int idx = getAppointmentIndexById(id);
		if (idx == -1) {
			return null;
		}

		return appointments.get(idx);
	}

	public List<Appointment> getAppointmentsByDate(LocalDateTime dateToSearch) {
		List<Appointment> appointmentsToReturn = new ArrayList<Appointment>();

		for (Appointment appt : appointments) {
			if (doesAppointmentDateHaveSameYearMonthDay(appt.getStartDateTime(), dateToSearch)) {
				appointmentsToReturn.add(appt);
			}
		}

		return appointmentsToReturn;
	}

	// Utility method to do a mediocre linear search for an appointment provided an
	// ID (not ideal to say the least, should find alternative!!!)
	private int getAppointmentIndexById(Integer id) {
		for (int i = 0; i < appointments.size(); i++) {
			if (appointments.get(i).getId() == id) {
				return i;
			}
		}

		return -1;
	}

	// Utility method to compare year, month, and day of two LocalDateTime objects
	private boolean doesAppointmentDateHaveSameYearMonthDay(LocalDateTime ldt1, LocalDateTime ldt2) {
		if (ldt1.getYear() == ldt2.getYear() && ldt1.getMonth() == ldt2.getMonth()
				&& ldt1.getDayOfMonth() == ldt2.getDayOfMonth()) {
			return true;
		}

		return false;
	}

}
