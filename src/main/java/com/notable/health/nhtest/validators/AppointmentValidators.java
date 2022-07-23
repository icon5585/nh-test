package com.notable.health.nhtest.validators;

import java.time.LocalDateTime;
import java.util.List;

import com.notable.health.nhtest.data.models.Appointment;
import com.notable.health.nhtest.data.models.Doctor;

/**
 * Class of static utility methods to validate appointments
 * 
 * @author Hank DeDona
 *
 */
public class AppointmentValidators {

	// There are better ways to write validators, more abstraction

	/**
	 * Validates that an {@link Appointment}s start date/time is on the 15's (i.e.
	 * is modulo by 15)
	 * 
	 * @param appointmentStartDateTime the {@link LocalDateTime} to check
	 * 
	 * @return <code>true</code> if it is on the 15's (00, 15, 30, 45),
	 *         <code>false</code> otherwise
	 */
	public static boolean validateAppointmentTimeIsOn15Minute(LocalDateTime appointmentStartDateTime) {
		if (appointmentStartDateTime.getMinute() % 15 != 0) {
			return false;
		}
		return true;
	}

	/**
	 * Validates that an {@link Appointment} that wants to be added to a given
	 * {@link Doctor}s schedule is not the 3rd one at the same date/time.
	 * 
	 * Note: This does NOT take into account appointments that start earlier and
	 * overlap. Need more time to implement that functionality
	 * 
	 * @param doctor           {@link Doctor} to add the {@link Appointment} to
	 * @param appointmentToAdd the {@link Appointment} we want to add
	 * @return <code>true</code> if the number of appointments at that date/time is
	 *         less than 3, <code>false</code> otherwise
	 */
	public static boolean validateAppointmentIsNot3rdAppointment(Doctor doctor, Appointment appointmentToAdd) {
		LocalDateTime appointmentToAddDateTime = appointmentToAdd.getStartDateTime();

		List<Appointment> existingAppointmentsForDay = doctor.getAppointmentsByDate(appointmentToAddDateTime);

		int appointmentsAtGivenTimeCounter = 0;

		// Note: THIS IS ONLY CHECKING FOR THE SAME DATE AND TIME! DOES NOT ACCOUNT FOR
		// APPOINTMENTS THAT HAPPEN TO START EARLIER AND OVERLAP!
		// NEED MORE TIME FOR THAT!
		for (Appointment existingAppt : existingAppointmentsForDay) {
			if (existingAppt.getStartDateTime().getHour() == appointmentToAddDateTime.getHour()
					&& existingAppt.getStartDateTime().getMinute() == appointmentToAddDateTime.getMinute()) {
				appointmentsAtGivenTimeCounter++;
			}
		}

		// If we haven't hit our 3rd appointment yet, return true, otherwise false
		return appointmentsAtGivenTimeCounter < 3;
	}

}
