package com.notable.health.nhtest.data.models;

import java.time.LocalDateTime;

import com.notable.health.nhtest.data.enums.AppointmentType;
import com.notable.health.nhtest.utils.IdGenerator;
import com.notable.health.nhtest.validators.AppointmentValidators;

/**
 * POJO representing an appointment
 * 
 * @author Hank DeDona
 *
 */
public class Appointment {

	private final int id;

	private final Patient patient;

	private final AppointmentType appointmentType;

	private final LocalDateTime startDateTime;

	private final int durationInMinutes;

	public Appointment(Patient patient, AppointmentType appointmentType, LocalDateTime startDateTime,
			int durationInMinutes) {
		if (!AppointmentValidators.validateAppointmentTimeIsOn15Minute(startDateTime)) {
			// Thought: Might be better to just throw the exception in the validator, no?
			throw new IllegalArgumentException("Appointment start times MUST be on the 15s");
		}

		id = IdGenerator.getNextNumber();
		this.patient = patient;
		this.appointmentType = appointmentType;
		this.startDateTime = startDateTime;
		this.durationInMinutes = durationInMinutes;
	}

	public Appointment(AppointmentRequest appointmentRequest) {
		id = IdGenerator.getNextNumber();

		Patient newPatient = null;

		switch (appointmentRequest.getAppointmentType()) {
		case NEW_PATIENT:
			newPatient = new Patient(appointmentRequest.getPatientFirstName(), appointmentRequest.getPatientLastName());
			break;
		case FOLLOW_UP:
			// TODO: Find existing patient... didn't build that in...
			newPatient = new Patient(appointmentRequest.getPatientFirstName(), appointmentRequest.getPatientLastName());
			break;
		}

		this.patient = newPatient;
		this.appointmentType = appointmentRequest.getAppointmentType();
		this.startDateTime = LocalDateTime.of(appointmentRequest.getYear(), appointmentRequest.getMonth(),
				appointmentRequest.getDay(), appointmentRequest.getHour(), appointmentRequest.getMinute());
		this.durationInMinutes = appointmentRequest.getDurationInMinutes();

	}

	public int getId() {
		return id;
	}

	public Patient getPatient() {
		return patient;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

}
